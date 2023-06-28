package com.kotlin.migration.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kotlin.migration.jpashop.domain.item.Item
import jakarta.persistence.*

@Entity
class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private val id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private val item: Item? = null

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private val order: Order? = null

    private val orderPrice = 0 // 주문 가격
    private val count = 0 // 주문 수량
}