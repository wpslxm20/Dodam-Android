package com.umc.dodam.src.main.myPage.diaryOrderService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface DiaryOrderInterface {

    // 전체 주문 조회
    @GET("/orders")
    fun getDiaryOrder(): Call<DiaryOrder>

    // 상세 주문 전체 조회
    @GET("/order-details")
    fun getDiaryOrderDetail(): Call<DiaryOrderDetail>

    // 주문 등록
    @POST("/orders")
    fun postDiaryOrderRegister(): Call<DiaryOrderRegister>

    // orderId로 상세 주문 등록
    @POST("/order-details/{orderId}")
    fun postDiaryOrderRegisterByOrderId(): Call<DiaryOrderRegisterByOrderId>

    // orderId를 만족하는 상세 주문 조회
    @GET("/orders-details/{orderId}")
    fun getDiaryOrderByOrderId(
        @Header("orderId") orderId: Int
    ): Call<DiaryOrderByOrderId>

    // userId를 만족하는 주문 조회
    @GET("/orders/{userId}")
    fun getDiaryOrderByUserId(
        @Header("userId") userId: Int
    ): Call<DiaryOrderByUserId>

    // orderId를 만족하는 주문 변경
    @PATCH("/orders/{orderId}")
    fun patchDiaryOrderChangeByOrderId(
        @Header("orderId") orderId: Int
    ): Call<DiaryOrderChangeByOrderId>

    // orderId를 만족하는 주문 삭제
    @PATCH("/orders/delete/{orderId}")
    fun patchDiaryOrderDeleteByOrderId(
        @Header("orderId") orderId: Int
    ): Call<DiaryOrderDeleteByOrderId>
}