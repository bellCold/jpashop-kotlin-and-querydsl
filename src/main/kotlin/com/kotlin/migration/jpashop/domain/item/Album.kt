package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Int,
    stockQuantity: Int,
    val artist: String
) : Item(name = name, price = price, stockQuantity = stockQuantity)
