package com.umc.dodam.src.main.myPage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentInquiryBinding
import com.umc.dodam.databinding.InquiryItemBinding
import com.umc.dodam.src.main.myPage.inquiryRecycler.InquiryAdapter
import com.umc.dodam.src.main.myPage.inquiryRecycler.InquiryItem
import java.text.FieldPosition

class InquiryFragment : Fragment() {

    private var _binding: FragmentInquiryBinding? = null
    private val binding get() = _binding!!
    lateinit var itemBinding: InquiryItemBinding

    // RecyclerView와 Adapter 연결
    private lateinit var inquiryAdapter: InquiryAdapter
    var inquiryList = mutableListOf<InquiryItem>()
    lateinit var mLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInquiryBinding.inflate(layoutInflater)
        itemBinding = InquiryItemBinding.inflate(layoutInflater)

        inquiryList.apply {
            add(InquiryItem(1,"배송", "언제 배송?", "내놔잇"))
            add(InquiryItem(2, "오류", "앱 오류나뮤?", "힝구뽕", "옥히~"))
            add(InquiryItem(3, "아1", "뿡1", "헿dkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"))
            add(InquiryItem(4, "아악2", "뿡2", "헿dkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"))
            add(InquiryItem(5, "아악3", "뿡3", "헿dkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"))
            add(InquiryItem(6, "아악4", "뿡4", "헿dkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"))
            add(InquiryItem(7, "아악5", "뿡5", "헿dkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"))
            add(InquiryItem(8, "아악6", "뿡6", "헿dkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView와 Adapter 연결
        inquiryAdapter = InquiryAdapter(this.inquiryList, inquiryClick = {})
        binding.inquiryItemRecyclerview.adapter = inquiryAdapter
        mLayoutManager = LinearLayoutManager(requireContext())
        binding.inquiryItemRecyclerview.layoutManager = mLayoutManager

        // click listener
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnInquiryWrite.setOnClickListener {
            // 화면 이동

            // 데이터 넘겨 받아서 추가
            inquiryList.apply {

                // 새로고침
                inquiryAdapter.notifyDataSetChanged()
            }
        }
    }

    // Fragment는 View 보다 더 오래 살아남기 때문에 한번 정리
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val TAG = "InquiryFragment"
        fun instance() = InquiryFragment()
    }

}