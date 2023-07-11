package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    val author: String? = null,
    val isbn: String? = null
) : Item(name = name, price = price, stockQuantity = stockQuantity)