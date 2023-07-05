package com.kotlin.migration.jpashop.domain

import com.kotlin.migration.jpashop.domain.item.Item
import jakarta.persistence.*

@Entity
class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long = 0,

    val name: String,

    @ManyToMany @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: MutableList<Item> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Category,

    @OneToMany(mappedBy = "parent")
    val child: MutableList<Category> = mutableListOf()
)