package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.OrderStatus

data class OrderSearch(var memberName: String?, var orderStatus: OrderStatus?)
