package com.umc.dodam.src.main.myPage.inquiryService

import com.google.gson.annotations.SerializedName
import java.util.*

// 회원 문의사항 전체 조회
data class Inquiry (
        @SerializedName("title") var title: String,
        @SerializedName("status") var status: Boolean,
        @SerializedName("createAt") var createAt: Date
)

// 개별 문의사항 상제 정보
data class InquiryDetail(
        @SerializedName("title") var title: Date,
        @SerializedName("content") var content: Date,
        @SerializedName("answer ") var answer : Date,
        @SerializedName("category") var category: Date,
        @SerializedName("imgPath") var imgPath: Date
)

// 문의 삭제
data class InquiryDelete(
        @SerializedName("result") var result: String
)

// 문의 수정
data class InquiryModify(
        @SerializedName("id") var id: Int,
        @SerializedName("userId") var userId: Int,
        @SerializedName("title") var title: String,
        @SerializedName("content") var content: String,
        @SerializedName("answer ") var answer : String,
        @SerializedName("status") var status: Boolean,
        @SerializedName("category") var category: String,
        @SerializedName("createAt") var createAt: Date,
        @SerializedName("updateAt") var updateAt: Date,
        @SerializedName("imgPath") var imgPath: String,
        @SerializedName("fileName ") var fileName : String
)

// 문의 등록
data class InquiryRegister(
        @SerializedName("id") var id: Int,
        @SerializedName("userId") var userId: Int,
        @SerializedName("title") var title: String,
        @SerializedName("content") var content: String,
        @SerializedName("answer ") var answer : String,
        @SerializedName("status") var status: Boolean,
        @SerializedName("category") var category: String,
        @SerializedName("createAt") var createAt: Date,
        @SerializedName("updateAt") var updateAt: Date,
        @SerializedName("imgPath") var imgPath: String,
        @SerializedName("fileName ") var fileName : String
)