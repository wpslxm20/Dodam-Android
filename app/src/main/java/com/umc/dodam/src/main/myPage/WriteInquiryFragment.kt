package com.umc.dodam.src.main.myPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentWriteInquiryBinding

class WriteInquiryFragment : Fragment() {

    private var _binding: FragmentWriteInquiryBinding? = null
    private val binding get() = _binding!!

    // 문의 카테고리, 문의 제목, 문의 내용 저장
    var inquiryCategory = ""
    var inquiryTitle = ""
    var inquiryContent = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWriteInquiryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // spinner(dropbox)와 data array + adapter 연결
        setOnSpinner()
        // click listener
        setOnClickListener()
    }

    private fun setOnSpinner() {
        val category = resources.getStringArray(R.array.inquiry_category_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, category)
        binding.spinnerCategory.adapter = adapter
    }

    private fun setOnClickListener() {
        // 뒤로 가기 버튼 기능

        // 문의 작성 버튼 클릭 시, 문의 카테고리, 문의 제목, 문의 내용 저장
        binding.btnInquiryWrite.setOnClickListener {
            inquiryCategory = binding.spinnerCategory.selectedItem.toString()
            inquiryTitle = binding.inputInquiryTitle.text.toString()
            inquiryContent = binding.inputInquiryContent.text.toString()

            // 뒤로 가기 or 홈으로 이동
        }
    }
}