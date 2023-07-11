package com.kotlin.migration.jpashop.service

import com.kotlin.migration.jpashop.domain.*
import com.kotlin.migration.jpashop.domain.item.Item
import com.kotlin.migration.jpashop.repository.ItemRepository
import com.kotlin.migration.jpashop.repository.MemberRepository
import com.kotlin.migration.jpashop.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
) {

    fun order(memberId: Long, itemId: Long, count: Int) {
        val member: Member = memberRepository.findById(memberId).orElseThrow { RuntimeException("Member not found") }
        val item: Item = itemRepository.findById(itemId).orElseThrow { RuntimeException("Item not found") }


        // 배송정보 생성
        val delivery = Delivery(
            address = member.address,
            status = DeliveryStatus.READY
        )

        val orderItem = OrderItem.createOrderItem(
            item = item,
            price = item.price,
            count = count
        )

        val order = Order.createOrder(member, delivery, orderItem)

        orderRepository.save(order)
    }


}
