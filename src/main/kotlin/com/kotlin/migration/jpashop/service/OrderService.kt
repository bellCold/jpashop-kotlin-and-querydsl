package com.kotlin.migration.jpashop.service

import com.kotlin.migration.jpashop.domain.*
import com.kotlin.migration.jpashop.domain.item.Item
import com.kotlin.migration.jpashop.repository.ItemRepository
import com.kotlin.migration.jpashop.repository.MemberRepository
import com.kotlin.migration.jpashop.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
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

        return orderRepository.save(order).id
    }


    @Transactional
    fun cancelOrder(orderId: Long) {
        orderRepository.findById(orderId).orElseThrow { RuntimeException("Order not found") }.cancel()
    }


    // Todo
//    fun findOrders(orderSearch: OrderSearch): List<Order> {
//        return orderRepository.findAllByString(orderSearch)
//    }
}
