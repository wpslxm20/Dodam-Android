package com.umc.dodam.src.main.myPage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentBuyDiaryBinding
import kotlinx.android.synthetic.main.fragment_buy_diary.*


class BuyDiaryFragment : Fragment() {

    private var _binding: FragmentBuyDiaryBinding? = null
    private val binding get() = _binding!!

    var deliverName = ""
    var deliverAddress = ""
    var babyName = ""
    var diaryTitle = ""
    var diaryPeriod = ""
    var diaryTemplate = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBuyDiaryBinding.inflate(layoutInflater)
        return binding.root

        setOnClickListener()
    }

    private fun setOnClickListener() {
        // 주소 선택 버튼
        binding.btnDeliverAddress.setOnClickListener {

        }

        // 다이어리 템플릿 선택 라디오 버튼
        binding.radioBtnDiaryPamphletGroup.setOnCheckedChangeListener {group, checkedId ->
            Log.d("msg", "radioBtngroupclick")
            when(checkedId) {
                R.id.radio_btn_diary_pamphlet_1 -> diaryTemplate = 1
                R.id.radio_btn_diary_pamphlet_2 -> diaryTemplate = 2
                R.id.radio_btn_diary_pamphlet_3 -> diaryTemplate = 3
            }
        }

        // 구매 버튼 클릭 시, 데이터 저장
        binding.btnBuyDiary.setOnClickListener {
            deliverName = binding.inputDeliverName.text.toString()
            deliverAddress = binding.inputDeliverAddress.text.toString()
            babyName = binding.inputBabyName.text.toString()
            diaryPeriod = binding.inputDiaryPeriodStart.text.toString() + " " + binding.inputDiaryPeriodEnd.text.toString()
        }
    }
}