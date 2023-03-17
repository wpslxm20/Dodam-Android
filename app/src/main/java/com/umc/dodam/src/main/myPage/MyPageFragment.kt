package com.umc.dodam.src.main.myPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentMyPageBinding
import com.umc.dodam.databinding.FragmentStepRegisterBinding

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    // binding을 사용할 때, get으로 받아오는 것이 좋음
    private val binding get() = _binding!!

    // login한 사용자인지 확인
    private val valid_user = true
    private var nickname = "도랑"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 사용자의 로그인 유무에 따라 보여지는 화면 조정
        if(valid_user) {
            binding.btnLogin.text = nickname
            binding.textPeriod.visibility = View.VISIBLE
            binding.btnProfileEdit.visibility = View.VISIBLE
            binding.btnLogout.visibility = View.VISIBLE
        } else {
            binding.textPeriod.visibility = View.GONE
            binding.btnProfileEdit.visibility = View.GONE
            binding.btnLogout.visibility = View.GONE
        }

        // Button ClickListener
        setOnClickListener()

    }

    private fun setOnClickListener() {
        // 로그인 화면으로 이동
        binding.btnLogin.setOnClickListener {

        }
        // 프로필 클릭 시, 회원 정보 수정 화면으로 이동
        binding.btnProfileEdit.setOnClickListener {

        }
        // 다이어리 관리 화면으로 이동
        binding.btnManageDiary.setOnClickListener {

        }
        // 문의 화면으로 이동
        binding.btnInquiry.setOnClickListener {

        }
        // 회원 탈퇴 화면으로 이동
        binding.btnMemberExit.setOnClickListener {

        }

    }
}
