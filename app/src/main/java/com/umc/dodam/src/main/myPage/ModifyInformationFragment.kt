package com.umc.dodam.src.main.myPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentModifyInformationBinding

class ModifyInformationFragment : Fragment() {

    private var _binding: FragmentModifyInformationBinding? = null
    private val binding get() = _binding!!

    var nickname = "dowon"
    var profileImage = R.drawable.btn_gradation_color
    var phoneNumber = "01011112222"
    var birth = "01608"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModifyInformationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 저장되어 있는 회원 정보 미리 set
        // 프로필 이미지 설정
        binding.btnProfile.setImageResource(profileImage)
        // 닉네임 설정
        binding.inputNickname.setText(nickname)
        // 휴대폰 번호 설정
        binding.inputPhoneNumber.setText(phoneNumber)
        // 생년월일 설정
        binding.inputBirth.setText(birth)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        // 닉네임 중복 검사
        binding.btnNicknameVerify.setOnClickListener {

        }

        // 휴대폰 인증 번호 전송
        binding.btnPhoneNumberVarify.setOnClickListener {

        }

        // 인증번호 확인
        binding.btnCertificationNumberVerify.setOnClickListener {

        }

        // 회원 정보 수정 완료
        binding.btnInformationModify.setOnClickListener {
            // 데이터 저장
            nickname = binding.inputNickname.text.toString()
            phoneNumber = binding.inputPhoneNumber.text.toString()
            birth = binding.inputBirth.text.toString()

            // 마이페이지 화면으로 이동

        }


    }
}