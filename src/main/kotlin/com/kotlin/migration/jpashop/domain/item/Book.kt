package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    name: String,
    price: Int,
    stockQuantity: Int,
    var author: String? = null,
    var isbn: String? = null
) : Item(name = name, price = price, stockQuantity = stockQuantity)