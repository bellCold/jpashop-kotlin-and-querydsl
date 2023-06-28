package com.kotlin.migration.jpashop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album : Item() {
    private val artist: String? = null
    private val etc: String? = null
}