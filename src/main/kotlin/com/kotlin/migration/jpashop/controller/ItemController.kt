package com.kotlin.migration.jpashop.controller

import com.kotlin.migration.jpashop.domain.item.Book
import com.kotlin.migration.jpashop.service.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/items/new")
    fun create(form: BookForm): String {
        itemService.saveItem(
            Book(
                name = form.name,
                price = form.price,
                stockQuantity = form.stockQuantity,
                author = form.author,
                isbn = form.isbn
            )
        )

        return "redirect:/"
    }

    @GetMapping("/items")
    fun list(model: Model): String {
        model.addAttribute("items", itemService.findItems())
        return "items/itemList"
    }

    @GetMapping("/items/{itemId}/edit")
    fun updateItemForm(@PathVariable itemId: Long, model: Model): String {
        val item = itemService.findOne(itemId) as Book

        val book = BookForm(
            id = item.id,
            name = item.name,
            price = item.price,
            stockQuantity = item.stockQuantity,
            author = item.author,
            isbn = item.isbn
        )

        model.addAttribute("form", book)
        return "items/updateItemForm"
    }

    @PostMapping("/items/{itemId}/edit")
    fun updateItem(@PathVariable itemId: Long, @ModelAttribute("form") form: BookForm): String {
        itemService.updateItem(
            itemId = itemId,
            name = form.name,
            price = form.price,
            stockQuantity = form.stockQuantity
        )
        return "redirect:/items"
    }
}