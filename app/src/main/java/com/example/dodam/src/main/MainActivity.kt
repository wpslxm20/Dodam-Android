package com.example.dodam.src.main

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dodam.R
import com.example.dodam.databinding.ActivityMainBinding
import com.example.dodam.src.main.diary.DiaryFragment
import com.example.dodam.src.main.home.HomeFragment
import com.example.dodam.src.main.myPage.MyPageFragment

class MainActivity : AppCompatActivity() {

    // Binding 객체 선언
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 앱을 시작할 때, 초기 화면을 Home으로 설정
        supportFragmentManager.beginTransaction().replace(R.id.main_frame, HomeFragment()).commitAllowingStateLoss()

        BottomNavigation()
    }

    private fun BottomNavigation() {
        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_frame, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_diary -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_frame, DiaryFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_mypage -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.main_frame, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.main_btm_nav
        }
    }
}