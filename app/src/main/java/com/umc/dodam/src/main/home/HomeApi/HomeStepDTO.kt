package com.umc.dodam.src.main.home.HomeApi

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HomeStepResponse(
    @SerializedName("memberNickName")
    val memberNickName: String,

    @SerializedName("nowStep")
    val nowStep: List<String>,

    @SerializedName("allStep")
    val allStep: List<StepDTO>,

    @SerializedName("dDay")
    val dDay : Int

    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑
)

data class StepDTO(
    @SerializedName("stepId")
    val stepId: Int,

    @SerializedName("stepName")
    val stepName: String,

    @SerializedName("startDate")
    val startDate: Date,

    @SerializedName("endDate")
    val endDate: Date,

    @SerializedName("stepOrder")
    val stepOrder: Int

)
