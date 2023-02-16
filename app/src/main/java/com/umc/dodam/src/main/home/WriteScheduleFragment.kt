package com.umc.dodam.src.main.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.umc.dodam.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.dodam.databinding.FragmentHomeBinding
import com.umc.dodam.databinding.FragmentWriteScheduleBinding

class WriteScheduleFragment() : BottomSheetDialogFragment(){
//    , Parcel
//    constructor(parcel: Parcel) : this() {
//    }
    private var _binding: FragmentWriteScheduleBinding? = null
    private val binding get() = _binding!!

//    lateinit var writeScheduleColorAdapter: WriteScheduleColorAdapter
//    val colorList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWriteScheduleBinding.inflate(inflater, container, false)
        val view = binding.root

        // 날짜 선택 시 해당 뷰 활성화
        binding.rgroupRepeat.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                binding.rbtnDateSelect.id -> {
                    binding.viewDateSelect.isVisible = true
                    binding.viewRepeat.isGone = true
                }
                else -> {
                    binding.viewRepeat.isVisible = true
                    binding.viewDateSelect.isGone = true
                }
            }
        }

        // 색깔 리스트
//        colorList.add(R.color.write_schedule_color1)
//        colorList.add(R.color.write_schedule_color2)
//        colorList.add(R.color.write_schedule_color3)
//        colorList.add(R.color.write_schedule_color4)

//        writeScheduleColorAdapter = WriteScheduleColorAdapter(requireContext(), colorList)
//        binding.rvWriteScheduleColor.adapter = writeScheduleColorAdapter
        return view
    }
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }
//    override fun dismiss() {
//        super.dismiss()
//    }
}

