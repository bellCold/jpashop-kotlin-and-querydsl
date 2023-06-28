package com.kotlin.migration.jpashop.domain.item

import com.kotlin.migration.jpashop.domain.Category
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private val id: Long? = null

    private val name: String? = null
    private val price = 0
    private val stockQuantity = 0

    @ManyToMany(mappedBy = "items")
    private val categories: List<Category> = ArrayList()
}