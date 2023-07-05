package com.kotlin.migration.jpashop.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    val id: Long? = null

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    val order: Order? = null

    @Embedded
    val address: Address? = null

    @Enumerated(EnumType.STRING)
    val status: DeliveryStatus? = null // READY, COMP

}