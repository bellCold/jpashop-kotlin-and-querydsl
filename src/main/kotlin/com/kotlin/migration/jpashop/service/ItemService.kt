package com.kotlin.migration.jpashop.service

import com.kotlin.migration.jpashop.domain.item.Item
import com.kotlin.migration.jpashop.repository.ItemRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    @Transactional
    fun updateItem(itemId: Long, name: String, price: Int, stockQuantity: Int) {
        itemRepository.findById(itemId).orElseThrow { RuntimeException("Item not found") }
            .update(name, price, stockQuantity)
    }

    fun findItems(): List<Item> {
        return itemRepository.findAll()
    }

    fun findOne(itemId: Long): Item {
        return itemRepository.findById(itemId).orElseThrow { RuntimeException("Item not found") }
    }
}