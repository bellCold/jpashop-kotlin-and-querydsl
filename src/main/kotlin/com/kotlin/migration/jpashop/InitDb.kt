package com.kotlin.migration.jpashop

import com.kotlin.migration.jpashop.domain.*
import com.kotlin.migration.jpashop.domain.item.Book
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InitDb(
    private val initService: InitService
) {

    @PostConstruct
    fun init() {
        initService.dbInit1()
        initService.dbInit2()
    }
}

@Component
@Transactional
class InitService(
    private val em: EntityManager
) {
    fun dbInit1() {
        val member = createMember(name = "userA", city = "서울", street = "1", zipcode = "11111")
        em.persist(member)

        val book1 = createBook(name = "JPA1 BOOK", price = 10000, stockQuantity = 100)
        em.persist(book1)

        val book2 = createBook(name = "JPA2 BOOK", price = 20000, stockQuantity = 100)
        em.persist(book2)

        val orderItem1 = OrderItem.createOrderItem(item = book1, orderPrice = 10000, count = 100)
        val orderItem2 = OrderItem.createOrderItem(item = book2, orderPrice = 20000, count = 2)

        val delivery = createDelivery(member)
        val order = Order.createOrder(member = member, delivery = delivery, orderItems = arrayOf(orderItem1, orderItem2))
        em.persist(order)
    }

    fun dbInit2() {
        val member = createMember(name = "userB", city = "진주", street = "2", zipcode = "2222")
        em.persist(member)

        val book1 = createBook(name = "SPRING1 BOOK", price = 20000, stockQuantity = 200)
        em.persist(book1)

        val book2 = createBook(name = "SPRING2 BOOK", price = 40000, stockQuantity = 300)
        em.persist(book2)

        val orderItem1 = OrderItem.createOrderItem(item = book1, orderPrice = 20000, count = 3)
        val orderItem2 = OrderItem.createOrderItem(item = book2, orderPrice = 40000, count = 4)

        val delivery = createDelivery(member = member)
        val order =
            Order.createOrder(member = member, delivery = delivery, orderItems = arrayOf(orderItem1, orderItem2))
        em.persist(order)
    }

    private fun createMember(name: String, city: String, street: String, zipcode: String): Member {
        return Member(
            name = name,
            address = Address(city = city, street = street, zipcode = zipcode)
        )
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        return Book(
            name = name,
            price = price,
            stockQuantity = stockQuantity,
        )
    }

    private fun createDelivery(member: Member): Delivery {
        return Delivery(address = member.address)
    }
}
