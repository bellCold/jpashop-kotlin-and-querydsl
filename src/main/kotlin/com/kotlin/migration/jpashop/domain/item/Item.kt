package com.kotlin.migration.jpashop.domain.item

import com.kotlin.migration.jpashop.exception.NotEnoughStockException
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Item(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    val id: Long = 0,

    var name: String,

    var price: Int = 0,

    var stockQuantity: Int = 0,
) {
    fun update(name: String, price: Int, stockQuantity: Int) {
        this.name = name
        this.price = price
        this.stockQuantity = stockQuantity
    }

    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        stockQuantity = restStock
    }

}