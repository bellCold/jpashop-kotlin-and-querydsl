package com.kotlin.migration.jpashop.domain

import jakarta.persistence.Embeddable

@Embeddable
class Address {
    private val city: String? = null
    private val street: String? = null
    private val zipcode: String? = null
}