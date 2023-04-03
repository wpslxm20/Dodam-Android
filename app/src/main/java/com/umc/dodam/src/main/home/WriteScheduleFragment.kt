package com.umc.dodam.src.main.home

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.umc.dodam.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.dodam.databinding.FragmentWriteScheduleBinding
import com.umc.dodam.src.main.Api.RetrofitBuilder
import com.umc.dodam.src.main.home.HomeApi.HomeInterface
import com.umc.dodam.src.main.myPage.MyPageApi.MyPageInterface
import retrofit2.Retrofit
import java.sql.Time
import java.util.*
import kotlin.properties.Delegates

class WriteScheduleFragment() : BottomSheetDialogFragment(){

    private var _binding: FragmentWriteScheduleBinding? = null
    private val binding get() = _binding!!

    // retrofit builder 선언
    private val retrofit: Retrofit = RetrofitBuilder.getInstance()
    private val api: HomeInterface = retrofit.create(HomeInterface::class.java)

    // writeSchedule input
    private var userId by Delegates.notNull<Int>()
    private var repeatStatus by Delegates.notNull<String>()
    private var startTime by Delegates.notNull<Time>()
    private var endTime by Delegates.notNull<Time>()
    private var color by Delegates.notNull<String>()

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
                R.id.rbtn_repeat_date_select -> {
                    binding.viewDateSelect.visibility = View.VISIBLE
                    binding.viewRepeat.visibility = View.GONE
                }
                else -> {
                    binding.viewRepeat.visibility = View.VISIBLE
                    binding.viewDateSelect.visibility = View.GONE
                }
            }
        }

        // 시간 timePicker
        binding.viewStartTime.setOnClickListener {
            var timeString = ""
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                timeString = "${hourOfDay}시 ${minute}분"
            }

            val timePicker = TimePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true)
            timePicker.window?.setBackgroundDrawableResource(R.color.transparency)
            timePicker.show()
        }

        binding.viewEndTime.setOnClickListener {
            var timeString = ""
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                timeString = "${hourOfDay}시 ${minute}분"
            }

            val timePicker = TimePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true)
            timePicker.window?.setBackgroundDrawableResource(R.color.transparency)
            timePicker.show()
        }


        // 저장 버튼 클릭
        binding.btnSave.setOnClickListener {

            //임시
            userId = 666
            startTime = Time(11,0,0)
            endTime = Time(12,0,0)

            when (binding.rgroupRepeat.checkedRadioButtonId) {
                R.id.rbtn_repeat_date_select -> {
                    repeatStatus = "날짜 선택"
                }
                R.id.rbtn_repeat_every_week -> {
                    repeatStatus = "매주"
                }
                R.id.rbtn_repeat_every_two_week -> {
                    repeatStatus = "격주"
                }
                R.id.rbtn_repeat_every_three_week -> {
                    repeatStatus = "3주"
                }
                R.id.rbtn_repeat_every_month -> {
                    repeatStatus = "한달"
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

    // 프래그먼트 크기 조절
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

