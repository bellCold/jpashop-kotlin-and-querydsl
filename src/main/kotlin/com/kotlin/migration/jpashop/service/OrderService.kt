package com.kotlin.migration.jpashop.service

import com.kotlin.migration.jpashop.domain.*
import com.kotlin.migration.jpashop.domain.item.Item
import com.kotlin.migration.jpashop.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderJpaRepository: OrderJpaRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository,
) {
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        val member: Member = memberRepository.findById(memberId).orElseThrow { RuntimeException("Member not found") }
        val item: Item = itemRepository.findById(itemId).orElseThrow { RuntimeException("Item not found") }

        // 배송정보 생성
        val delivery = Delivery(
            address = member.address,
            status = DeliveryStatus.READY
        )

        val orderItem = OrderItem.createOrderItem(
            item = item,
            orderPrice = item.price,
            count = count
        )

        val order = Order.createOrder(
            member = member,
            delivery = delivery,
            orderItems = arrayOf(orderItem)
        )

        return orderJpaRepository.save(order).id
    }

    @Transactional
    fun cancelOrder(orderId: Long) {
        val order = orderJpaRepository.findById(orderId).orElseThrow { RuntimeException("Order with ID $orderId not found") }
        order.cancel()
    }

    fun findOrders(orderSearch: OrderSearch): List<Order>? {
        return null
    }
}
