package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie : Item() {
    private val director: String? = null
    private val actor: String? = null
}