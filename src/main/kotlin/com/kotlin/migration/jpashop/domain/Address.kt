package com.kotlin.migration.jpashop.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val city: String,
    val street: String,
    val zipcode: String
)
