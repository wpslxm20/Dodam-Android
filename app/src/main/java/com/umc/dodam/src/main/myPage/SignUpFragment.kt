package com.umc.dodam.src.main.myPage

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.drawToBitmap
import com.umc.dodam.R
import com.umc.dodam.databinding.FragmentSignUpBinding
import com.umc.dodam.databinding.FragmentStepRegisterBinding
import com.umc.dodam.src.main.MainActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.processNextEventInCurrentThread

class SignUpFragment : Fragment(), View.OnClickListener{

    private var _binding: FragmentSignUpBinding? = null
    // binding을 사용할 때, get으로 받아오는 것이 좋음
    private val binding get() = _binding!!

    private var activity: com.umc.dodam.src.main.MainActivity?= null

    // 변수
    var profileImage: Bitmap? = null
    var progileImageExist: Boolean = false
    var idInput: String? = null
    var passwordInput: String? = null
    var passwordcheckInput: String? = null
    var nicknameInput: String? = null
    var phoneNumInput: String? = null
    var certificationInput: String? = null
    var birthInput: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        // 프로필 이미지 클릭 시, 갤러리 호출
        binding.btnProfile.setOnClickListener {
            navigatePhotos()
            progileImageExist = true
        }

        // id 중복 확인
        binding.btnIdVerify.setOnClickListener {
            idInput = binding.inputId.text.toString()
            Log.d("id_input", idInput.toString())
        }

        // 비밀번호 일치하는 지 확인 (수정 필요 )
        binding.inputPasswordCheck.setOnClickListener {
            passwordInput = binding.inputPassword.text.toString()
            passwordcheckInput = binding.inputPasswordCheck.text.toString()
            if(passwordInput == passwordcheckInput) {
                Toast.makeText(getActivity(), "비밀번호가 일치합니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
            Log.d("password_input", passwordInput.toString())
            Log.d("password_check_input", passwordcheckInput.toString())
        }

        // 닉네임 중복 확인
        binding.btnNicknameVerify.setOnClickListener {
            nicknameInput = binding.inputNickname.text.toString()
            Log.d("nickname_input", nicknameInput.toString())
        }

        // 휴대폰 번호 확인
        binding.btnPhoneNumberVarify.setOnClickListener {
            phoneNumInput = binding.inputPhoneNumber.text.toString()
            Log.d("phone_input", phoneNumInput.toString())
        }

        // 인증번호 확인
        binding.btnCertificationNumberVerify.setOnClickListener {
            certificationInput = binding.inputCertificationNumber.text.toString()
            Log.d("certi_input", certificationInput.toString())

        }

        // 생년월일 저장
        birthInput = binding.inputBirth.text.toString()

        // 회원가입 버튼
        binding.btnSignUp.setOnClickListener {
            // 화면 전환
            Toast.makeText(getActivity(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    // Gallery 연결
    private fun navigatePhotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
//        intent.putExtra("crop", true)
        startActivityForResult(intent,2000)
    }

    // Gallery에서 image 받아오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            Log.d("msg", "onActivityResult != Activity.RESULT_OK")
            return
        }
        when (requestCode) {
            2000 -> {
                val selectedImageURI: Uri? = data?.data
//                cropImage(selectedImageURI)
                if (selectedImageURI != null) {
                    var result =
                        binding.btnProfile.setImageURI(selectedImageURI)
                    // 갤러리에서 선택한 이미지 프로필로 설정
                    profileImage = binding.btnProfile.drawToBitmap()
                    // 기본 프로필 이미지 숨기기
//                    binding.btnProfile.visibility = View.GONE
                } else {
                    Log.d("msg", "onActivity selectedImageURI == null")
                }
            }
            else -> {
                Log.d("msg", "onActivity requestCode != 2000")
            }
        }
    }

    // Gallery에서 불러온 이미지를 size에 맞게 crop
    private fun cropImage(uri: Uri?) {
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(Activity())
    }
}