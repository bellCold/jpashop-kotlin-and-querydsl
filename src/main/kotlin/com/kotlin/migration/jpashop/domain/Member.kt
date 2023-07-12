package com.kotlin.migration.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    val id: Long = 0,

    var name: String,

    @Embedded
    val address: Address,

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    val orders: MutableList<Order> = mutableListOf()
)