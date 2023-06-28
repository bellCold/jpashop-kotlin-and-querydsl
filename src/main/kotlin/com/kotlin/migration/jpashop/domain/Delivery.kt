package com.kotlin.migration.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private val id: Long? = null

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private val order: Order? = null

    @Embedded
    private val address: Address? = null

    @Enumerated(EnumType.STRING)
    private val status: DeliveryStatus? = null //READY, COMP

}