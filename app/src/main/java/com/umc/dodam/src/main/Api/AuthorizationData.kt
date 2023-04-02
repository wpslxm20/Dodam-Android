package com.umc.dodam.src.main.Api

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.umc.dodam.src.main.Api.AuthorizationData.PreferenceKeys.ACCESS_TOKEN
import com.umc.dodam.src.main.Api.AuthorizationData.PreferenceKeys.LOGIN_CHECK
import com.umc.dodam.src.main.myPage.LoginApi.LoginInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.tokenDataStore by preferencesDataStore("TOKEN_DATASTORE")
private val Context.loginCheckDataStore by preferencesDataStore("LOGIN_CHECK_DATASTORE")

class AuthorizationData @Inject constructor(
//    private val loginApi: LoginInterface,
    private val context: Context
) {
//    override suspend fun getKakaoToken(kakaoOauthRequest: KakaoOauthRequest): JWT {
//        return loginApi.getKakaoToken(kakaoOauthRequest).toJWT()
//    }
//
//    override suspend fun getNaverToken(naverOauthRequest: NaverOauthRequest): JWT {
//        return loginApi.getNaverToken(naverOauthRequest).toJWT()
//    }

    private object PreferenceKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
//        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
        val LOGIN_CHECK = booleanPreferencesKey("login_check")
    }



    suspend fun saveToken(token: String) {
        context.tokenDataStore.edit { prefs ->
            prefs[ACCESS_TOKEN] = token
//            prefs[REFRESH_TOKEN] = token.last()
        }
        // AccessToken, RefreshToken 이 제대로 들어온 여부를 확인하는 boolean 값
        context.loginCheckDataStore.edit { prefs ->
            prefs[LOGIN_CHECK] = true
        }
    }

    fun getToken(): Flow<String> {
        return context.tokenDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    exception.printStackTrace()
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
//            .map { prefs ->
//                prefs.asMap().values.toList().map {
//                    it.toString()
//                }
//            }
            .map { prefs ->
                prefs[ACCESS_TOKEN] ?: ""
            }
    }

    suspend fun getIsLogin(): Flow<Boolean> {
        return context.loginCheckDataStore.data
            .map { prefs ->
                prefs[LOGIN_CHECK] ?: false
            }
    }
}