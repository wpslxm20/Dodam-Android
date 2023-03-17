package com.umc.dodam.src.main.myPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentExitMemberBinding

class ExitMemberFragment : Fragment() {

    private var _binding: FragmentExitMemberBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExitMemberBinding.inflate(layoutInflater)
        return binding.root

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnMemberExit.setOnClickListener {
            // 회원 정보 삭제

            // 메인 화면으로 이동

            // 로그인 해제 
        }
    }
}