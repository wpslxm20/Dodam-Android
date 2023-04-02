package com.umc.dodam.src.main.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentHomeBinding
import com.umc.dodam.src.main.Api.AuthorizationData
import com.umc.dodam.src.main.Api.RetrofitBuilder
import com.umc.dodam.src.main.home.HomeApi.HomeInterface
import com.umc.dodam.src.main.home.HomeApi.HomeStepResponse
import com.umc.dodam.src.main.home.homeStepRecycler.HomeStepAdapter
import com.umc.dodam.src.main.home.homeStepRecycler.HomeStepItem
import com.umc.dodam.src.main.home.homeCalenderRecycler.HomeCalenderAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import retrofit2.Callback


class HomeFragment : Fragment() {

    private var job: Job? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var authorizationData: AuthorizationData
    private var token : String = ""


    // retrofit builder 선언
    private val retrofit: Retrofit = RetrofitBuilder.getInstance()
    private val api: HomeInterface = retrofit.create(HomeInterface::class.java)

    // 달력을 표시하기 위한 현재 선택되어 있는 날짜(월을 표현하기 위함)
    lateinit var selectedDate: LocalDate;

    //home 화면의 단계 recycler 연결
    lateinit var homeStepAdapter: HomeStepAdapter
    val homeStepList = mutableListOf<HomeStepItem>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //현재 날짜
        selectedDate = LocalDate.now()

        // 토큰 값 받아오기 위함
        authorizationData = AuthorizationData(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        //home 화면의 일정 recycler 어댑터 연결
        homeStepAdapter = HomeStepAdapter(this.homeStepList)
        binding.rvHomeStep.adapter = homeStepAdapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 단계 등록 버튼 클릭 시 단계 등록 페이지로 이동
        binding.btnGoStepRegister.setOnClickListener{
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction
                .replace(R.id.nav_host_fragment, RegisterStepFragment())
                .addToBackStack(null)
                .commit()

        }


        //달력 내부의 플러스 버튼을 누르면 일정등록페이지로 이동
        binding.btnGoWriteShedule.setOnClickListener(){

            val bottomSheetFragment = WriteScheduleFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }

        //월 텍스트뷰 세팅
        setMonthView();

        //전월, 익월 버튼
        binding.btnPrev.setOnClickListener(){
            selectedDate = selectedDate.minusMonths(1)
            setMonthView()
        }
        binding.btnNext.setOnClickListener(){
            selectedDate = selectedDate.plusMonths(1)
            setMonthView()
        }

        //달력 recycler 연결
        val calenderRecyclerView = binding.rvCalenderDay

        // homeStepRecycler api 연동
        getStepMain()
    }

    private fun getStepMain() {
        // 비동기
        job = CoroutineScope(Main).launch {
            // 디바이스에 저장되어 있는 토큰 가져오기
            token = authorizationData.getToken().first().toString()

            // token 값 헤더로 넘겨줌
            // 메인 화면에 시술 단계 가져오기
            api.getStep(token).enqueue(object: Callback<HomeStepResponse> {
                override fun onFailure(call: Call<HomeStepResponse>, t: Throwable) {
                    Log.d("실패", t.message.toString())
                }

                override fun onResponse(call: Call<HomeStepResponse>, response: Response<HomeStepResponse>) {
                    var temp: HomeStepResponse? = response.body()
                    if (temp != null) {
                        binding.tvNickname.text = temp.memberNickName

                        // homeStepList 만들기
                        updateHomeStepList(temp)
                    }

                }
            })
        }
//        token = authorizationData.getToken().toString()


    }

    private fun settingHome(temp: HomeStepResponse?){
        binding.tvNickname.text = temp!!.memberNickName
    }
    private fun updateHomeStepList(temp:HomeStepResponse?){
        // 시술 단계 가져오기에 성공하면
        // 시술 단계 순서에 의해 정렬해서 리스트 만들기

        var sortedList = temp!!.allStep.sortedBy { it.stepOrder }
        for(i in sortedList){
            homeStepList.add(HomeStepItem(i.stepName))
        }
        Log.d("getStepMain 테스트", homeStepList.toString())
        homeStepAdapter.notifyDataSetChanged()

    }

    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }


    //달력 세팅 시작
    @RequiresApi(Build.VERSION_CODES.O)
    private fun monthFromDate(date:LocalDate) : String{
        var formatter:DateTimeFormatter = DateTimeFormatter.ofPattern("MM")
        return date.format(formatter)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        // 년월 텍스트뷰 세팅
        binding.tvCalenderMonth.setText(monthFromDate(selectedDate))

        val dayList: ArrayList<String> = createDateArray(selectedDate)

        //레이아웃 설정(열 7개)
        binding.rvCalenderDay.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvCalenderDay.adapter = HomeCalenderAdapter(dayList)
    }

    //날짜 생성 메소드
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createDateArray(date: LocalDate): ArrayList<String>{
        val dayList: ArrayList<String> = arrayListOf()
        val yearMonth: YearMonth = YearMonth.from(date)

        //해당 월 마지막 날짜 가져오기
        val lastDay: Int = yearMonth.lengthOfMonth();

        //해당 월 첫번째 날 가져오기
        val firstDay: LocalDate = selectedDate.withDayOfMonth(1);

        //첫번째 날 요일 가져오기
        var dayOfWeek: Int = firstDay.dayOfWeek.value

        if(dayOfWeek==7) dayOfWeek = 0

        for(i: Int in 1..41){
            // 날짜가 없는 칸엔 빈 칸으로 채워넣기
            if (i <= dayOfWeek || i > lastDay + dayOfWeek){
                dayList.add("")
            }else{
                dayList.add((i-dayOfWeek).toString())
            }
        }
        return dayList
    }

    // 달력 세팅 끝

}