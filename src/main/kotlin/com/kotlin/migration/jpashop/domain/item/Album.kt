package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Long,
    stockQuantity: Int,
    val artist: String
) : Item(name, price, stockQuantity)
