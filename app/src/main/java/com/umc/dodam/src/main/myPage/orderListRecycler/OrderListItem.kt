package com.umc.dodam.src.main.myPage.orderListRecycler

data class OrderListItem (
    var orderNum: Int = 0,
    var courierService: String = "",
    var invoiceNum: Int = 0,
    var deliverStep: Int = 1
)