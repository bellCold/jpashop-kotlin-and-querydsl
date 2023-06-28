package com.kotlin.migration.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private val id: Long? = null

    private val name: String? = null

    @Embedded
    private val address: Address? = null

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private val orders: List<Order> = ArrayList()
}