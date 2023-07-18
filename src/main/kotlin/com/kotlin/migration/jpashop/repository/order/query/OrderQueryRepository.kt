package com.kotlin.migration.jpashop.repository.order.query

import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class OrderQueryRepository(private val em: EntityManager) {

    fun findOrderQueryDtos(): List<OrderQueryDtos> {
        val result = findOrders()
        result.forEach { o ->
            val orderItems = findOrderItem(o.orderId)
            o.orderItems = orderItems
        }
        return result
    }

    private fun findOrderItem(orderId: Long): List<OrderItemQueryDto> {
        return em.createQuery(
            "select new com.kotlin.migration.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                    " from OrderItem oi" +
                    " join oi.item i" +
                    " where oi.order.id = :orderId", OrderItemQueryDto::class.java
        )
            .setParameter("orderId", orderId)
            .resultList
    }

    private fun findOrders(): List<OrderQueryDtos> {
        return em.createQuery(
            "select new com.kotlin.migration.jpashop.repository.order.query.OrderQueryDtos(o.id, m.name, o.orderDate, o.status, d.address)" +
                    " from Order o" +
                    " join o.member m" +
                    " join o.delivery d", OrderQueryDtos::class.java
        ).resultList
    }

}