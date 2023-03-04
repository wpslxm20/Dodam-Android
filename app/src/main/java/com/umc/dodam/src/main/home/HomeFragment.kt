package com.umc.dodam.src.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentHomeBinding
import com.umc.dodam.src.main.home.homeStepRecycler.HomeStepAdapter
import com.umc.dodam.src.main.home.homeStepRecycler.HomeStepItem
import com.google.android.material.bottomsheet.BottomSheetDialog


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //home 화면의 단계 recycler 연결
    lateinit var homeStepAdapter: HomeStepAdapter
    val homeStepList = mutableListOf<HomeStepItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoWriteShedule.setOnClickListener(){
            val writeScheduleView = layoutInflater.inflate(R.layout.fragment_write_schedule, null)
            val writeScheduleDialog = BottomSheetDialog(requireContext())
            writeScheduleDialog.setContentView(writeScheduleView)

            writeScheduleDialog.show()
        }
    }
    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }
}