package com.example.dodam.src.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
//import androidx.navigation.NavController
//import androidx.navigation.fragment.NavHostFragment
import com.example.dodam.R
import com.example.dodam.databinding.ActivityMainBinding
import com.example.dodam.src.main.diary.DiaryFragment
import com.example.dodam.src.main.home.HomeFragment
import com.example.dodam.src.main.home.MedicalRecordFragment
import com.example.dodam.src.main.home.StepRegisterFragment
import com.example.dodam.src.main.myPage.SignInFragment


class MainActivity : AppCompatActivity() {

    // NavController 선언
    private lateinit var navController: NavController

    // Binding 객체 선언
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 앱을 시작할 때, 초기 화면을 Home으로 설정
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commitAllowingStateLoss()
        // BottomNavigationBar 전환 함수
        BottomNavigation()

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = navHostFragment!!.navController

//        binding.mainBtmNav.setupWithNavController(navController)
    }

    private fun BottomNavigation() {
        binding.mainBtmNav.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.menu_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, MedicalRecordFragment())
                            .commitAllowingStateLoss()
                        Log.d("msg", "menu_home work")
                    }
                    R.id.menu_diary -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, DiaryFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.sign_in -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, SignInFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.main_btm_nav
        }
    }
}