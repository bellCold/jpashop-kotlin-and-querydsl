package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}