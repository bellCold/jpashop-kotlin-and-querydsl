package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String,
    price: Long,
    stockQuantity: Int,
    val author: String,
    val isbn: String
) : Item(name, price, stockQuantity)