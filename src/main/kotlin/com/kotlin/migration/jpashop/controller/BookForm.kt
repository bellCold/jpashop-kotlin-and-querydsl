package com.kotlin.migration.jpashop.controller

data class BookForm(
    var id: Long = 0 ,
    var name: String = "",
    var price: Int = 0,
    var stockQuantity: Int = 0,
    var author: String? = "",
    var isbn: String? = ""
)