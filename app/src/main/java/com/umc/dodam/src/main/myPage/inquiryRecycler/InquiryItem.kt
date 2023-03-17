package com.umc.dodam.src.main.myPage.inquiryRecycler

data class InquiryItem(
    var inquiryId: Int,
    var inquiryCategory: String = "",
    var inquiryTitle: String = "",
    var inquiryContent: String = "",
    var inquiryAnswer: String = "아직 답변이 없습니다."
)
