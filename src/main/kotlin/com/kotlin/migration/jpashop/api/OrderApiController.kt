package com.kotlin.migration.jpashop.api

import com.kotlin.migration.jpashop.api.response.ApiResponse
import com.kotlin.migration.jpashop.domain.Address
import com.kotlin.migration.jpashop.domain.Order
import com.kotlin.migration.jpashop.domain.OrderItem
import com.kotlin.migration.jpashop.repository.OrderJpaRepository
import com.kotlin.migration.jpashop.repository.order.query.OrderQueryDtos
import com.kotlin.migration.jpashop.repository.order.query.OrderQueryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class OrderApiController(
    private val orderJpaRepository: OrderJpaRepository,
    private val orderQueryRepository: OrderQueryRepository,
) {

    @GetMapping("/api/v1/orders")
    fun ordersV1(): List<Order> {
        val orders = orderJpaRepository.findAll()

        for (order in orders) {
            order.member.name
            order.delivery.address

            val orderItems: MutableList<OrderItem> = order.orderItems

            for (orderItem in orderItems) {
                orderItem.item
            }
        }

        return orders
    }

    @GetMapping("/api/v2/orders")
    fun ordersV2(): ApiResponse<List<OrderDto>> {
        return ApiResponse(
            orderJpaRepository.findAll()
                .map(::OrderDto)
                .toList()
        )
    }

    @GetMapping("/api/v3/orders")
    fun ordersV3(): ApiResponse<List<OrderDto>> {
        return ApiResponse(
            orderJpaRepository.findAllWithItem()
                .map(::OrderDto)
                .toList()
        )
    }

    @GetMapping("/api/v3.1/orders")
    fun ordersV3Page(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): ApiResponse<List<OrderDto>> {
        return ApiResponse(
            orderJpaRepository.findAllWithMemberDelivery(pageable = PageRequest.of(page, size))
                .map(::OrderDto)
                .toList()
        )
    }

    @GetMapping("/api/v4/orders")
    fun ordersV4(): ApiResponse<List<OrderQueryDtos>> {
        return ApiResponse(orderQueryRepository.findOrderQueryDtos())
    }

    data class OrderDto(
        val orderId: Long,
        val name: String,
        val orderDate: LocalDateTime,
        val address: Address?,
        val orderItems: List<OrderItemDto>,
    ) {
        constructor(order: Order) : this(
            orderId = order.id,
            name = order.member.name,
            orderDate = order.orderDate,
            address = order.member.address,
            orderItems = order.orderItems.map(::OrderItemDto).toList()
        )
    }

    data class OrderItemDto(val itemName: String, val orderPrice: Int, val count: Int) {
        constructor(orderItem: OrderItem) : this(
            itemName = orderItem.item.name,
            orderPrice = orderItem.orderPrice,
            count = orderItem.count
        )
    }
}