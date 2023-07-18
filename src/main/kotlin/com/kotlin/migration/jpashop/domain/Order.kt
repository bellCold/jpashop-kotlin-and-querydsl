package com.kotlin.migration.jpashop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    val orderItems: MutableList<OrderItem> = mutableListOf(),

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    val delivery: Delivery,

    val orderDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus
) {
    // 연관관계 메소드
    private fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }


    // 생성 메소드
    companion object {
        fun createOrder(member: Member, delivery: Delivery, orderItems: Array<OrderItem>): Order {
            val order = Order(
                member = member,
                delivery = delivery,
                status = OrderStatus.ORDER,
                orderDate = LocalDateTime.now()
            )

            orderItems.forEach { orderItem ->
                order.addOrderItem(orderItem)
            }

            return order
        }
    }

    // 비지니스 로직
    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL
        orderItems.forEach { orderItem ->
            orderItem.cancel()
        }
    }

}