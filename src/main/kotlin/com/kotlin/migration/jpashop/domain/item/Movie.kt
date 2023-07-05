package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(
    name: String,
    price: Long,
    stockQuantity: Int,
    val director: String,
    val author: String,
) : Item(name, price, stockQuantity)
