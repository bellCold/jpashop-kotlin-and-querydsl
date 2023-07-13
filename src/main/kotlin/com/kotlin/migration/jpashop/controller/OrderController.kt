package com.kotlin.migration.jpashop.controller

import com.kotlin.migration.jpashop.repository.OrderSearch
import com.kotlin.migration.jpashop.service.ItemService
import com.kotlin.migration.jpashop.service.MemberService
import com.kotlin.migration.jpashop.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class OrderController(
    private val orderService: OrderService,
    private val memberService: MemberService,
    private val itemService: ItemService
) {

    @GetMapping("/order")
    fun createForm(model: Model): String {
        model.addAttribute("members", memberService.findMembers())
        model.addAttribute("items", itemService.findItems())

        return "order/orderForm"
    }

    @PostMapping("/order")
    fun order(
        @RequestParam memberId: Long,
        @RequestParam itemId: Long,
        @RequestParam count: Int
    ): String {
        orderService.order(memberId = memberId, itemId = itemId, count = count)
        return "redirect:/orders"
    }

    // 현재 에러
    @GetMapping("/orders")
    fun orderList(@ModelAttribute("orderSearch") orderSearch: OrderSearch, model: Model): String {
        println("test")
        model.addAttribute("orders", orderService.findOrders(orderSearch))
        return "order/orderList"
    }

    @PostMapping("/orders/{orderId}/cancel")
    fun cancelOrder(@PathVariable orderId: Long): String {
        orderService.cancelOrder(orderId = orderId)
        return "redirect:/orders"
    }
}