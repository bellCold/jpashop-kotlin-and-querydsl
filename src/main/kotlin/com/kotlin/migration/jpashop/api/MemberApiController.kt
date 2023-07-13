package com.kotlin.migration.jpashop.api

import com.kotlin.migration.jpashop.domain.Member
import com.kotlin.migration.jpashop.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberApiController(private val memberService: MemberService) {


    @PostMapping("/ap1/v1/members")
    fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        return CreateMemberResponse(id = memberService.join(member))
    }

    data class CreateMemberResponse(val id: Long? = null)
}