package com.umc.dodam.src.main.home

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.umc.dodam.R
import com.umc.dodam.src.main.MainActivity
import com.umc.dodam.src.main.home.homeRegisterStepRecycler.RegisterStepItem
import kotlinx.android.synthetic.main.dialog_step_add.*
import kotlinx.android.synthetic.main.dialog_step_add.edit_step_name
import kotlinx.android.synthetic.main.dialog_step_modify.*
import java.util.*

class AddStepDialog (context: Context) {

    private val dialog = Dialog(context)
    private lateinit var onClickListener: ButtonClickListener
    var activity: com.umc.dodam.src.main.MainActivity? = null

    // value 저장
    var stepName = ""
    var startDate = ""
    var endDate = ""

    interface ButtonClickListener {
        // 단계 이름, 단계 기간 넘겨줘야 함
        fun onClicked(StepName: String, StepStartDate: Date, StepEndDate: Date)
    }

    fun setOnClickListener(listener: ButtonClickListener) {
        onClickListener = listener
    }

    // dialog를 띄우는 함수
    fun showDialog()
    {
        dialog.setContentView(R.layout.dialog_step_add)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        // DatePicker

        // 저장
        dialog.btn_save.setOnClickListener {
            // 단계 이름 저장
            stepName = dialog.edit_step_name.text.toString()
            // 단계 기간 저장
            startDate = dialog.step_period_add_start.text.toString()
            endDate = dialog.step_period_add_end.text.toString()

            dialog.dismiss()
        }
    }
}