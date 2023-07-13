package com.kotlin.migration.jpashop.controller

import com.kotlin.migration.jpashop.domain.Address
import com.kotlin.migration.jpashop.domain.Member
import com.kotlin.migration.jpashop.service.MemberService
import jakarta.validation.Valid
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(private val memberService: MemberService) {

    @GetMapping("/members/new")
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm())
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(@Valid form: MemberForm, result: BindingResult): String {

        if (result.hasErrors()) {
            return "members/createMemberForm"
        }

        val member = Member(name = form.name, address = Address(city = form.city, street = form.street, zipcode = form.zipcode))

        memberService.join(member)

        return "redirect:/"
    }

    @GetMapping("/members")
    fun list(model: Model): String {
        model.addAttribute("members", memberService.findMembers())
        return "members/membersList"
    }

}