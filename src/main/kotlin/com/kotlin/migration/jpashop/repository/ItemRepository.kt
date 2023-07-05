package com.kotlin.migration.jpashop.repository

import com.kotlin.migration.jpashop.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long>