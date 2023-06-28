package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book : Item() {
    private val author: String? = null
    private val isbn: String? = null
}