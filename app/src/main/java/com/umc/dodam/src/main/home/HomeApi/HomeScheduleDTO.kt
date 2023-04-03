package com.umc.dodam.src.main.home.HomeApi

import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.*

data class HomeScheduleRequest(
    @SerializedName("scheduleId")
    val scheduleId: Int? = null,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("repeatStatus")
    val repeatStatus: String,

    @SerializedName("selectDate")
    val selectDate: String? = null,

    @SerializedName("selectDay")
    val selectDay: Int? = null,

    @SerializedName("startDate")
    val startDate: Date? = null,

    @SerializedName("endDate")
    val endDate: Date? = null,

    @SerializedName("startTime")
    val startTime: Time,

    @SerializedName("endTime")
    val endTime: Time,

    @SerializedName("color")
    val color: String,

    // @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
    // @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑
)