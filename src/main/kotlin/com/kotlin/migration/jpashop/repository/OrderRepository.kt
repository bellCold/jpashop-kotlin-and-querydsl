package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.Order
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import org.springframework.util.StringUtils

class OrderRepository(private val em: EntityManager) {
    fun findAllByString(orderSearch: OrderSearch): List<Order?> {
        var jpql = "select o from Order o join o.member m"
        var isFirstCondition = true

        //주문 상태 검색

        //주문 상태 검색
        if (orderSearch.orderStatus != null) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
            jpql += " o.status = :status"
        }

        //회원 이름 검색

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.memberName)) {
            if (isFirstCondition) {
                jpql += " where"
                isFirstCondition = false
            } else {
                jpql += " and"
            }
            jpql += " m.name like :name"
        }

        var query: TypedQuery<Order?> = em.createQuery(jpql, Order::class.java)
            .setMaxResults(1000)

        if (orderSearch.orderStatus != null) {
            query = query.setParameter("status", orderSearch.orderStatus)
        }
        if (StringUtils.hasText(orderSearch.memberName)) {
            query = query.setParameter("name", orderSearch.memberName)
        }

        return query.resultList
    }
}