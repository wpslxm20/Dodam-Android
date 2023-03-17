package com.umc.dodam.src.main.home

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.umc.dodam.R
import com.umc.dodam.databinding.DialogStepModifyBinding
import com.umc.dodam.databinding.StepRegisterItemBinding
import com.umc.dodam.src.main.MainActivity
import com.umc.dodam.src.main.home.homeRegisterStepRecycler.RegisterStepAdapter
import com.umc.dodam.src.main.home.homeRegisterStepRecycler.RegisterStepItem
import kotlinx.coroutines.withContext
import java.util.*
import kotlinx.android.synthetic.main.dialog_step_modify.*

// value 저장
var stepName = ""
var startDate = ""
var endDate = ""

class ModifyStepDialog (
    context: Context
//    val deleteClick: (id: Int) -> Unit
) {

    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener
    var activity: com.umc.dodam.src.main.MainActivity? = null

    private lateinit var stepRegisterAdapter: RegisterStepAdapter

    interface OnDialogClickListener {
        // 단계 이름, 단계 기간 넘겨줘야 함
        fun onClicked(StepName: String, StepStartDate: Date, StepEndDate: Date)
    }

    fun setOnClickListener(listener: OnDialogClickListener) {
        onClickListener = listener
    }

    // dialog를 띄우는 함수
    fun showDialog() {
        dialog.setContentView(R.layout.dialog_step_modify)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        // DatePicker
        dialog.btn_calendar_step_modify.setOnClickListener {
            Log.d("msg", "btn cal working")
            val cal = Calendar.getInstance()    //Calender View 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                startDate = "${year}년 ${month+1}월 ${dayOfMonth}일"
                dialog.step_period_modify_start.text = startDate
            }
//            DatePickerDialog(,R.style.DatePickerStyle, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        dialog.btn_calendar_step_modify2.setOnClickListener {
            Log.d("msg", "btn cal working")
            val cal = Calendar.getInstance()    //Calender View 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                endDate = "${year}년 ${month+1}월 ${dayOfMonth}일"
                dialog.step_period_modify_end.text = endDate
            }
//            DatePickerDialog(requireContext(), R.style.DatePickerStyle, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // 수정 완료 버튼
        dialog.btn_step_modify.setOnClickListener {
            // 단계 이름 저장
            stepName = dialog.edit_step_name.text.toString()
            // 단계 기간 저장
            startDate = dialog.step_period_modify_start.text.toString()
            endDate = dialog.step_period_modify_end.text.toString()

            dialog.dismiss()
        }

        // 삭제 버튼
        dialog.btn_step_delete.setOnClickListener {

            dialog.dismiss()
        }
    }

    private fun deleteStep() {
        deleteFlag = 1
        dialog.dismiss()
    }
}