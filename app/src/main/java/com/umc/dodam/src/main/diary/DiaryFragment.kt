package com.umc.dodam.src.main.diary

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
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentDiaryBinding
import com.umc.dodam.databinding.FragmentHomeBinding
import com.umc.dodam.src.main.diary.diaryCalenderRecycler.DiaryCalenderAdapter
import com.umc.dodam.src.main.home.homeCalenderRecycler.HomeCalenderAdapter
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter


class DiaryFragment : Fragment() {

    private var _binding: FragmentDiaryBinding? = null
    private val binding get() = _binding!!

    // 달력을 표시하기 위한 현재 선택되어 있는 날짜(월을 표현하기 위함)
    lateinit var selectedDate: LocalDate;

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
        _binding = FragmentDiaryBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM")
        return date.format(formatter)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun setMonthView() {
        // 년월 텍스트뷰 세팅
        binding.tvCalenderMonth.setText(monthFromDate(selectedDate))

        val dayList: ArrayList<String> = createDateArray(selectedDate)

        //레이아웃 설정(열 7개)
        binding.rvCalenderDay.layoutManager = GridLayoutManager(requireContext(), 7)
        binding.rvCalenderDay.adapter = DiaryCalenderAdapter(dayList)
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