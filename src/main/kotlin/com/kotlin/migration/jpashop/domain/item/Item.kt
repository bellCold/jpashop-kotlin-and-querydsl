package com.kotlin.migration.jpashop.domain.item

import com.kotlin.migration.jpashop.domain.Category
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Item(

    val name: String,

    val price: Long,

    val stockQuantity: Int,

    @ManyToMany(mappedBy = "items")
    val categories: MutableList<Category> = mutableListOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    fun update(name: String, price: Int, stockQuantity: Int) {
        
    }
}