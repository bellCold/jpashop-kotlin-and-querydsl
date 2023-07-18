package com.kotlin.migration.jpashop.api

import com.kotlin.migration.jpashop.api.response.ApiResponse
import com.kotlin.migration.jpashop.domain.Address
import com.kotlin.migration.jpashop.domain.Order
import com.kotlin.migration.jpashop.domain.OrderStatus
import com.kotlin.migration.jpashop.repository.OrderRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class OrderSimpleController(
    private val orderRepository: OrderRepository,
) {

    @GetMapping("/api/v1/simple-orders")
    fun ordersV1(): List<Order> {
        return orderRepository.findAll()
    }

    @GetMapping("/api/v2/simple-orders")
    fun ordersV2(): ApiResponse<List<SimpleOrderDto>> {
        return ApiResponse(
            orderRepository.findAll()
                .map(::SimpleOrderDto)
                .toList()
        )
    }

    @GetMapping("/api/v3/simple-orders")
    fun ordersV3(): ApiResponse<List<SimpleOrderDto>> {
        return ApiResponse(
            orderRepository.findAllWithMemberDelivery()
                .map(::SimpleOrderDto)
                .toList()
        )
    }

    data class SimpleOrderDto(
        val orderId: Long,
        val name: String,
        val orderDate: LocalDateTime,
        val orderStatus: OrderStatus,
        val address: Address?,
    ) {
        constructor(order: Order) : this(
            order.id,
            order.member.name,
            order.orderDate,
            order.status,
            order.delivery.address
        )
    }
}