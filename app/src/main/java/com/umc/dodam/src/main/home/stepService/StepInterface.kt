package com.umc.dodam.src.main.home.stepService

import com.umc.dodam.src.main.home.*
import retrofit2.Call
import retrofit2.http.*

interface StepInterface {

    // 메인페이지 - 단계 관리
    @GET("/step/main")
    fun getMainStep(
        @Header("Authorization") authToken: String
    ): Call<Step>

    // 단계 등록 - 시작 날짜, 모든 단계 리스트
    @GET("/step/enroll")
    fun getStepRegister(
        @Header("Authorization") authToken: String
    ): Call<StepRegister>

    // 단계 등록 - 순서 변경 성공 문구
    @Headers("Content-Type : application/json")
    @PUT("/step/enroll")
    fun putStepRegister(
        @Header("Authorization") authToken: String,
    ): Call<StepChange>

    // 선택한 단계의 정보 불러옴
    @GET("step/select")
    fun getStepSelect(): Call<StepSelect>

    // 단계 수정 성공
    @Headers("Content-Type : application/json")
    @PUT("/step/select")
    fun putStepSelect(): Call<StepModify>

    // 단계 삭제 성공 문구
    @DELETE("/step/select/{stepId}")
    fun deleteStep(): Call<StepDelete>

    // 시작 날짜 설정 성공 문구
    @PUT("/step/startDate/{startDate}")
    fun putStepStartDateSet(
        @Header("Authorization") authToken: String
    ): Call<StepStartDateSet>

    // 단계 추가 생성 성공 문구
    @Headers("Content-Type : application/json")
    @POST("/step")
    fun postStepAdd(
        @Header("Authorization") authToken: String
    ): Call<StepAdd>

}