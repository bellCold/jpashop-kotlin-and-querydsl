package com.kotlin.migration.jpashop.controller

data class BookForm(
    val id: Long,
    val name: String,
    val price: Int,
    val stockQuantity: Int,
    val author: String,
    val isbn: String
)