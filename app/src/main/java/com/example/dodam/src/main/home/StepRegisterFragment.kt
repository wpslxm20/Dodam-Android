package com.example.dodam.src.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dodam.R
import com.example.dodam.databinding.FragmentStepRegisterBinding

class TestData (
    private var step_name: String ?= null,
    private var step_period: String ?= null
) {
    fun getStepName(): String? {
        return step_name
    }

    fun setStepName(step_name: String){
        this.step_name = step_name
    }

    fun getStepPeriod(): String? {
        return step_period
    }

    fun setStepPeriod(step_period: String){
        this.step_period = step_period
    }
}

class StepRegisterFragment : Fragment() {

    private lateinit var binding: FragmentStepRegisterBinding
    private lateinit var StepRegisterAdapter: StepRegisterAdapter

    var dataList: ArrayList<TestData> = arrayListOf(
        TestData("시술 단계 명1", "기간1"),
        TestData("시술 단계 명1", "기간1"),
        TestData("시술 단계 명1", "기간1"),
        TestData("시술 단계 명1", "기간1"),
        TestData("시술 단계 명1", "기간1")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

}
