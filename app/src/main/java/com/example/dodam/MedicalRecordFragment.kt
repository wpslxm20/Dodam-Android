package com.example.dodam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dodam.databinding.FragmentLoginBinding
import com.example.dodam.databinding.FragmentMedicalRecordBinding


class MedicalRecordFragment : Fragment() {

    private var _binding: FragmentMedicalRecordBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMedicalRecordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }

}