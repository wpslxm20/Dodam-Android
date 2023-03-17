package com.umc.dodam.src.main.home

import com.google.gson.annotations.SerializedName
import java.util.*

// 메인페이지 - 단계 관리
data class Step(
    @SerializedName("memberNickName") var memberNickName: String,
    @SerializedName("dDay") var dDay: Int,
    @SerializedName("nowStep") var nowStep: List<String>,
    @SerializedName("allStep") var allStep: List<String>
)

// 단계 등록 - 시작 날짜, 모든 단계 리스트
data class StepRegister (
    @SerializedName("startDate") var startDate: Date
)

// 단계 등록 - 순서 변경 성공 문구
data class StepChange (
    @SerializedName("result") var result: String
)

// 선택한 단계의 정보 불러옴
data class StepSelect (
    @SerializedName("stepId") var stepId: Int,
    @SerializedName("userId") var userId: Int,
    @SerializedName("stepName") var stepName: String,
    @SerializedName("startDate") var startDate: Date,
    @SerializedName("endDate") var endDate: Date
)

// 단계 수정 성공
data class StepModify(
    @SerializedName("result") var result: String
)

// 단계 삭제 성공 문구
data class StepDelete(
    @SerializedName("result") var result: String
)

// 시작 날짜 설정 성공 문구
data class StepStartDateSet(
    @SerializedName("result") var result: String
)

// 단계 추가 생성 성공 문구
data class StepAdd(
    @SerializedName("result") var result: String
)