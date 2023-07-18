package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.Order
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderJpaRepository : JpaRepository<Order, Long> {
    @Query(
        "select distinct o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d" +
                " join fetch o.orderItems oi" +
                " join fetch oi.item i"
    )
    fun findAllWithItem(): List<Order>

    @Query("select distinct o from Order o join fetch o.member m join fetch o.delivery d")
    fun findAllWithMemberDelivery(pageable: Pageable): List<Order>
}