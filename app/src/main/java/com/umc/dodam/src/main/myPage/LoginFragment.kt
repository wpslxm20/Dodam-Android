package com.umc.dodam.src.main.myPage

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.umc.dodam.databinding.FragmentLoginBinding
import com.umc.dodam.src.main.Api.AuthorizationData
import com.umc.dodam.src.main.Api.DataStore
import com.umc.dodam.src.main.Api.RetrofitBuilder
import com.umc.dodam.src.main.home.HomeApi.HomeInterface
import com.umc.dodam.src.main.myPage.LoginApi.LoginInterface
import com.umc.dodam.src.main.myPage.LoginApi.LoginRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Callback


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // retrofit builder 선언
    private val retrofit: Retrofit = RetrofitBuilder.getInstance()
    private val api: LoginInterface = retrofit.create(LoginInterface::class.java)

    // 자동 로그인 선언
    private lateinit var authorizationData: AuthorizationData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 자동 로그인
        authorizationData = AuthorizationData(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //카카오톡 소셜 로그인
        binding.btnLoginKakao.setOnClickListener(){
//            val serviceTerms = listOf("service")
//            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
//                UserApiClient.instance.loginWithKakaoTalk( requireContext(), serviceTerms = serviceTerms)
//                { token, error ->
//                    if (error != null) { Log.e(TAG, "로그인 실패", error) }
//                    else if (token != null) { Log.i(TAG, "로그인 성공 ${token.accessToken}") }
//                }
//            } else {
//                UserApiClient.instance.loginWithKakaoAccount(requireContext(), serviceTerms = serviceTerms)
//                { token, error ->
//                    if (error != null) { Log.e(TAG, "로그인 실패", error) }
//                    else if (token != null) { Log.i(TAG, "로그인 성공 ${token.accessToken}") }
//                }
//            }
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
            }
        }



        //로그인 api 연동
        binding.btnLogin.setOnClickListener{
            //입력된 아이디, 비밀번호 값을 가져와 LoginDTO 형식의 변수에 저장
            val inputId = binding.tvId.text.toString()
            val inputPassword = binding.tvPw.text.toString()

            val inputLogin = LoginRequest(inputId, inputPassword)

            api.login(inputLogin).enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful) {
                        // 로그인 버튼 클릭 시 헤더에서 토큰값 받아오기
                        var token = response.headers().get("Authorization")
                        Log.d("성공", token.toString())

                        // token 디바이스에 저장 -> 자동 로그인을 위해
                        CoroutineScope(IO).launch {
                            authorizationData.saveToken(token.toString())
//                            Log.d("성공boolean", authorizationData.getIsLogin().first().toString())
//                            Log.d("성공token", authorizationData.getToken().first())
                        }

                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("test", "실패$t")
                }
            })
        }





    }



    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
        }
    }


    // 프래그먼트가 destroy (파괴) 될때
    override fun onDestroyView() {
        // 프래그먼트는 뷰보다 더 오래 살아남는다고 한다.
        // onDestroyView 에서 binding class 인스턴스 참조를 정리해주어야 한다.
        _binding = null
        super.onDestroyView()
    }


}

