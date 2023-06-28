package com.kotlin.migration.jpashop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private val member: Member? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    private val orderItems: List<OrderItem> = ArrayList()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    private val delivery: Delivery? = null

    private val orderDate: LocalDateTime? = null //주문시간


    @Enumerated(EnumType.STRING)
    private val status: OrderStatus? = null

}