package com.umc.dodam.src.main.home

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Parcel
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
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
        // 색깔 리스트
//        colorList.add(R.color.write_schedule_color1)
//        colorList.add(R.color.write_schedule_color2)
//        colorList.add(R.color.write_schedule_color3)
//        colorList.add(R.color.write_schedule_color4)

//        writeScheduleColorAdapter = WriteScheduleColorAdapter(requireContext(), colorList)
//        binding.rvWriteScheduleColor.adapter = writeScheduleColorAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 날짜 선택 시 해당 뷰 활성화
        binding.rgroupRepeat.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                R.id.rbtn_date_select -> {

                    binding.viewDateSelect.visibility = View.VISIBLE
                    binding.viewRepeat.visibility = View.GONE
                }
                else -> {
                    binding.viewRepeat.visibility = View.VISIBLE
                    binding.viewDateSelect.visibility = View.GONE
                }
            }
        }
    }
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.isDraggable = false
        }
        return dialog
    }

//여기서부터 getWindowHeight()까지 프래그먼트 크기 조절
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
////        val dialog = BottomSheetDialog(requireContext(), theme).apply {
////            behavior.state = BottomSheetBehavior.STATE_EXPANDED
////            behavior.isDraggable = false
////        }
//
//        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
//        dialog.setOnShowListener{
//            val bottomSheetDialog = it as BottomSheetDialog
//            //다이얼로그 크기 설정
//            setupRatio(bottomSheetDialog)
//        }
//        return dialog
//    }
//
//    private fun setupRatio(bottomSheetDialog: BottomSheetDialog){
//        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
//        val behavior = BottomSheetBehavior.from<View>(bottomSheet)
//        val layoutParams = bottomSheet!!.layoutParams
//        layoutParams.height = getBottomSheetDialogDefaultHeight()
//        bottomSheet.layoutParams = layoutParams
//        behavior.state = BottomSheetBehavior.STATE_EXPANDED
//    }
//
//    private fun getBottomSheetDialogDefaultHeight(): Int {
//        return getWindowHeight() * 90 / 100
//    }
//
//    private fun getWindowHeight(): Int {
//        // Calculate window height for fullscreen use
//        val displayMetrics = DisplayMetrics()
//        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
//        return displayMetrics.heightPixels
//    }
//    override fun dismiss() {
//        super.dismiss()
//    }
}

