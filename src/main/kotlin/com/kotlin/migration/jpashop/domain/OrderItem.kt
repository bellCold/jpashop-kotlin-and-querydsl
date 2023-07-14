package com.kotlin.migration.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.kotlin.migration.jpashop.domain.item.Item
import jakarta.persistence.*

@Entity
class OrderItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    val item: Item,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null,

    val orderPrice: Int = 0, // 주문 가격
    val count: Int = 0 // 주문 수량
) {
    fun cancel() {
        item.addStock(this.count)
    }

    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            return OrderItem(
                item = item,
                orderPrice =  orderPrice,
                count = count
            ).also { item.removeStock(count) }
        }
    }
}