package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.Order
import jakarta.persistence.EntityManager
import jakarta.persistence.TypedQuery
import org.springframework.stereotype.Repository
import org.springframework.util.StringUtils

@Repository
class OrderRepository(private val em: EntityManager) {

    fun save(order: Order): Order {
        em.persist(order)
        return order
    }

    fun findById(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAll(): List<Order> {
        return em.createQuery("select o from Order o", Order::class.java)
            .resultList
    }

    fun findAllByString(orderSearch: OrderSearch): List<Order?> {
        var jpql = "select o from Order o join o.member m"
        var isFirstCondition = true

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

    fun findAllWithItem(): List<Order> {
        return em.createQuery(
            "select distinct o from Order o" +
                    " join fetch o.member m" +
                    " join fetch o.delivery d" +
                    " join fetch o.orderItems oi" +
                    " join fetch oi.item i", Order::class.java
        ).resultList
    }

    fun findAllWithMemberDelivery(offset: Int, limit: Int): List<Order> {
        return em.createQuery(
            "select o from Order o" +
                    " join fetch o.member m" +
                    " join fetch o.delivery d", Order::class.java)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .resultList
    }

    fun findAllWithMemberDelivery(): List<Order> {
        return em.createQuery(
            "select o from Order o" +
                    " join fetch o.member m" +
                    " join fetch o.delivery d", Order::class.java)
            .resultList
    }
}