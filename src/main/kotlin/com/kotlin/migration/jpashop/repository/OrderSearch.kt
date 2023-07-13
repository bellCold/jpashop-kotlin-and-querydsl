package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.OrderStatus

data class OrderSearch(val memberName: String, val orderStatus: OrderStatus)
