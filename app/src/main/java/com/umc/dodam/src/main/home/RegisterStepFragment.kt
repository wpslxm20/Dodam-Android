package com.umc.dodam.src.main.home

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentStepRegisterBinding
import com.umc.dodam.src.main.home.homeRegisterStepRecycler.RegisterStepAdapter
import com.umc.dodam.src.main.home.homeRegisterStepRecycler.RegisterStepItem
import com.umc.dodam.src.main.home.stepService.StepClient
import com.umc.dodam.src.main.home.stepService.StepInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDate
import java.util.*

var deleteFlag = 0

class RegisterStepFragment() : Fragment(), View.OnClickListener {

    private var _binding: FragmentStepRegisterBinding? = null
    // binding을 사용할 때, get으로 받아오는 것이 좋음
    private val binding get() = _binding!!

    // StepClient의 instance 불러오기
    private val retrofit: Retrofit = StepClient.getInstance()
    // retrofit이 interface 구현
    private val api: StepInterface = retrofit.create(StepInterface::class.java)
    private val autoToken = "1"

    // DatePicker 변수
    var dateString = ""

    // 단계와 관련된 변수
    lateinit var allstepStartDate: String
    lateinit var stepName : String
    lateinit var stepStartDate: Date
    lateinit var stepEndDate: Date

    // 시술 단계 Recycler에 Adapter와 data 연결
    private lateinit var stepRegisterAdapter: RegisterStepAdapter
    val stepRegisterList = mutableListOf<RegisterStepItem>()

    // RecyclerView Drag&Drop 콜백 함수
    private val itemTouchHelper by lazy { ItemTouchHelper(ItemTouchCallback(stepRegisterAdapter)) }

    private lateinit var modifyStepDialog: ModifyStepDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 서버에 저장된 단계 불러오기
        getMainStepClient()
        Log.d("msg", "step register work")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStepRegisterBinding.inflate(layoutInflater)

        stepRegisterList.apply {
            add(RegisterStepItem(1,"배란 유도 주사", "2020년 1월 1일", "2020년 1월 2일"))
            add(RegisterStepItem(2, "배란 유도 주사2", "2020년 1월 1일", "2020년 1월 2일"))
            add(RegisterStepItem(3, "배란 유도 주사3", "2020년 1월 1일", "2020년 1월 2일"))
            add(RegisterStepItem(4, "배란 유도 주사4", "2020년 1월 1일", "2020년 1월 2일"))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recycler View Click Listener - 시술 단계 수정 dialog
        stepRegisterAdapter = RegisterStepAdapter(
            this.stepRegisterList,
            stepClick = {
                // RecyclerView item 클릭 시, 수정 dialog 호출
                val dialog = ModifyStepDialog(requireActivity())
                dialog.showDialog()

//                dialog.setOnClickListener(object: ModifyStepDialog.OnDialogClickListener {
//                    override fun onClicked(StepName: String, StepStartDate: Date, StepEndDate: Date) {
//                        stepName = StepName
//                        stepStartDate = StepStartDate
//                        stepEndDate = StepEndDate
//                    }
//                })

                if(deleteFlag == 1) {
                    Log.d("msg", "delete")
                    stepRegisterList.removeAt(it)
                    stepRegisterAdapter.notifyDataSetChanged()
                }
            })
        binding.stepRegisterRecyclerview.adapter = stepRegisterAdapter
        itemTouchHelper.attachToRecyclerView(binding.stepRegisterRecyclerview)

        // Button ClickListener
        setOnClickListener()
    }

    private fun setOnClickListener() {
        // 시작날짜 옆 Calender 아이콘 클릭 시 DatePicker Dialog 호출
        binding.btnCalendar.setOnClickListener {
            Log.d("msg", "btn cal working")
            val cal = Calendar.getInstance()    //Calender View 만들기
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                binding.textStartDate.text = dateString
            }
            DatePickerDialog(requireContext(), R.style.DatePickerStyle, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // 단계 추가 Dialog 호출
        binding.btnNewStepAdd.setOnClickListener {
            val dialog = AddStepDialog(requireActivity())
            dialog.showDialog()

            dialog.setOnClickListener(object: AddStepDialog.ButtonClickListener {
                override fun onClicked(StepName: String, StepStartDate: Date, StepEndDate: Date) {
                    stepName = StepName
                    stepStartDate = StepStartDate
                    stepEndDate = StepEndDate
                }
            })
        }

        // 저장 버튼 클릭 시
        binding.btnSave.setOnClickListener {
            // 시작 날짜, 시술 단계 저장
            allstepStartDate = binding.textStartDate.text.toString()
            // 시술 단계는 stepRegisterList에 저장됨



            // "저장되었습니다." Toast 메시지
            Toast.makeText(getActivity(), "저장되었습니다.", Toast.LENGTH_SHORT).show()
            // 화면 전환(main으로)

        }
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // 서버에 저장되어 있는 단계 불러오기
    private fun getMainStepClient() {
        api.getMainStep(autoToken).enqueue(object: Callback<Step>{
            // 전송 실패
            override fun onFailure(call: Call<Step>, t: Throwable) {
                Log.d("msg", "t.mesage")
            }

            override fun onResponse(call: Call<Step>, response: Response<Step>) {
                var temp = response.body().toString()
            }
        })
    }

    companion object {
        private const val TAG = "StepRegisterFragment"
        fun instance() = RegisterStepFragment()
    }
}
