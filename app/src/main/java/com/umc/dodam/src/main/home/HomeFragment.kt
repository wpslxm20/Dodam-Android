package com.umc.dodam.src.main.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.umc.dodam.databinding.FragmentHomeBinding
import com.umc.dodam.src.main.home.homeStepRecycler.HomeStepAdapter
import com.umc.dodam.src.main.home.homeStepRecycler.HomeStepItem
import com.umc.dodam.src.main.home.homeCalenderRecycler.HomeCalenderAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //home화면의 일정 목록
        homeStepList.apply {
            add(HomeStepItem("배란 유도 주사"))
            add(HomeStepItem("배란 주사"))
            add(HomeStepItem("난자 / 정자 채취"))
            add(HomeStepItem("배아 이식"))
            add(HomeStepItem("임신 확인 검사"))
        }

        //home 화면의 일정 recycler 어댑터 연결
        homeStepAdapter = HomeStepAdapter(this.homeStepList)
        binding.rvHomeStep.adapter = homeStepAdapter

        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        val calenderRecyclerView : RecyclerView = binding.rvCalenderDay
    }
    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }

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

        Log.d("dayOfWeek", dayOfWeek.toString())
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

}