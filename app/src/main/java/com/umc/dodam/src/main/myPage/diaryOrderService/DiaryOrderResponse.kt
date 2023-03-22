package com.umc.dodam.src.main.myPage.diaryOrderService

import com.google.gson.annotations.SerializedName

// 전체 주문 조회
data class DiaryOrder(
    @SerializedName("orderId") var orderId: Int,
    @SerializedName("userId") var userId: Int,
    @SerializedName("address") var address: String,
    @SerializedName("babyName") var babyname: String,
    @SerializedName("diaryTitle") var diaryTitle: String,
    @SerializedName("startDate") var startDate: String,
    @SerializedName("endDate") var endDate: String,
    @SerializedName("templateNo") var templateNo: Int,
    @SerializedName("createTime") var createTime: String,
    @SerializedName("updateTime") var updateTime: String,
    @SerializedName("isDeleted") var isDeleted: String,
    @SerializedName("deletedTime") var deletedTime: String,
)

// 상세 주문 전체 조회
data class DiaryOrderDetail(
    @SerializedName("ordreDetaild") var ordreDetaild: Int,
    @SerializedName("orderId") var orderId: Int,
    @SerializedName("deliveryService") var deliveryService: String,
    @SerializedName("invoiceNo") var invoiceNo: String,
    @SerializedName("deliveryStatus") var deliveryStatus: String
)

// 주문 등록
data class DiaryOrderRegister(
    @SerializedName("result") var result: String
)

// orderId로 상세 주문 등록
data class DiaryOrderRegisterByOrderId(
    @SerializedName("result") var result: String
)

// userId를 만족하는 주문 조회
data class DiaryOrderByUserId(
    @SerializedName("orderId") var orderId: Int,
    @SerializedName("userId") var userId: Int,
    @SerializedName("address") var address: String,
    @SerializedName("babyName") var babyName: String,
    @SerializedName("diaryTitle") var diaryTitle: String,
    @SerializedName("startDate") var startDate: String,
    @SerializedName("endDate") var endDate: String,
    @SerializedName("templateNo") var templateNo: Int,
    @SerializedName("createTime") var createTime: String,
    @SerializedName("updateTime") var updateTime: String,
    @SerializedName("isDeleted") var isDeleted: String,
    @SerializedName("deletedTime") var deletedTime: String
)

// orderId를 만족하는 상세 주문 조회
data class DiaryOrderByOrderId(
    @SerializedName("orderDetailId") var orderDetailId: Int,
    @SerializedName("orderId") var orderId: Int,
    @SerializedName("deliveryService") var deliveryService: String,
    @SerializedName("invoiceNo") var invoiceNo: String,
    @SerializedName("deliveryStatus") var deliveryStatus: String
)

// orderId를 만족하는 주문 변경
data class DiaryOrderChangeByOrderId(
    @SerializedName("result") var result: String
)

// orderId를 만족하는 주문 삭제
data class DiaryOrderDeleteByOrderId(
    @SerializedName("result") var result: String
)