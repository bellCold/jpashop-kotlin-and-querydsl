package com.kotlin.migration.jpashop.domain

import com.kotlin.migration.jpashop.domain.item.Item
import jakarta.persistence.*

@Entity
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private val id: Long? = null

    private val name: String? = null

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    private val items: List<Item> = ArrayList()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private val parent: Category? = null

    @OneToMany(mappedBy = "parent")
    private val child: List<Category> = ArrayList()

}