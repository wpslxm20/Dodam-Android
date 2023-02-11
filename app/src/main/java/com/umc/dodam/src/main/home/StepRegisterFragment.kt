package com.umc.dodam.src.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.dodam.databinding.FragmentStepRegisterBinding
import com.umc.dodam.src.main.home.homeStepRegisterRecycler.StepRegisterAdapter
import com.umc.dodam.src.main.home.homeStepRegisterRecycler.StepRegisterItem

class StepRegisterFragment : Fragment() {

    private var _binding: FragmentStepRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var StepRegisterAdapter: StepRegisterAdapter
    val stepRegisterList = mutableListOf<StepRegisterItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStepRegisterBinding.inflate(layoutInflater)

        stepRegisterList.apply {
            add(StepRegisterItem("배란 유도 주사", "20200101"))
            add(StepRegisterItem("배란 유도 주사", "20200101"))
            add(StepRegisterItem("배란 유도 주사", "20200101"))
            add(StepRegisterItem("배란 유도 주사", "20200101"))
        }

        StepRegisterAdapter = StepRegisterAdapter(this.stepRegisterList)
        binding.stepRegisterRecyclerview.adapter = StepRegisterAdapter

        return binding.root
    }

    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }

}
