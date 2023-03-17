package com.umc.dodam.src.main.myPage.inquiryService

import retrofit2.Call
import retrofit2.http.*

interface InquiryInterface {

    // 회원 문의사항 전체 조회
    @GET("/inquiries")
    fun getInquiry(): Call<Inquiry>

    // 개별 문의사항 상제 정보
    @GET("/inquiry/{id}")
    fun getInquiryDetail(
        @Header("id") id: Int
    ): Call<InquiryDetail>

    // 문의 삭제
    @DELETE("/inquiry/{id}")
    fun deleteInquiry(
        @Header("id") id: Int
    ): Call<InquiryDelete>

    // 문의 수정
    @PUT("/inquiry/{id}")
    fun putInquiryModify(
        @Header("id") id: Int
    ): Call<InquiryModify>

    // 문의 등록
    @POST("/inquiry/new")
    fun postInquiryRegister(): Call<InquiryRegister>

}