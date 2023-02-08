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

class MyPageFragment : Fragment() {

    private lateinit var binding: FragmentMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            Log.d("msg", "sign in btn working")
            view?.findNavController()?.navigate(R.id.action_myPageFragment_to_signInFragment)
        }
    }
}
