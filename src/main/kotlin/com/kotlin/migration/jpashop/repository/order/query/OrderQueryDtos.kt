package com.kotlin.migration.jpashop.repository.order.query

import com.kotlin.migration.jpashop.domain.Address
import com.kotlin.migration.jpashop.domain.OrderStatus
import java.time.LocalDateTime

data class OrderQueryDtos(
    val orderId: Long,
    val name: String,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val address: Address,
    var orderItems: List<OrderItemQueryDto>,
) {
    constructor(orderId: Long, name: String, orderDate: LocalDateTime, orderStatus: OrderStatus, address: Address)
            : this(orderId, name, orderDate, orderStatus, address, emptyList())
}