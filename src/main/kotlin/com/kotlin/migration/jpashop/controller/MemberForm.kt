package com.kotlin.migration.jpashop.controller

import jakarta.validation.constraints.NotEmpty

data class MemberForm(
    @NotEmpty
    var name: String = "",
    var city: String = "",
    var street: String = "",
    var zipcode: String = ""
)
