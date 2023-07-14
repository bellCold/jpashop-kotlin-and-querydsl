package com.kotlin.migration.jpashop.api

import com.kotlin.migration.jpashop.api.response.ApiResponse
import com.kotlin.migration.jpashop.domain.Member
import com.kotlin.migration.jpashop.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class MemberApiController(private val memberService: MemberService) {


    @PostMapping("/api/v1/members")
    fun saveMemberV1(@RequestBody @Valid member: Member): CreateMemberResponse {
        return CreateMemberResponse(id = memberService.join(member))
    }

    @PostMapping("/api/v2/members")
    fun saveMemberV2(@RequestBody @Valid request: CreateMemberRequest): CreateMemberResponse {
        val member = Member(name = request.name)
        return CreateMemberResponse(id = memberService.join(member))
    }

    @PutMapping("/api/v2/members/{id}")
    fun updateMemberV2(
        @PathVariable id: Long,
        @RequestBody @Valid request: UpdateMemberRequest,
    ): UpdateMemberResponse {
        memberService.update(id, request.name)
        val findMember = memberService.findOne(id)
        return UpdateMemberResponse(id = findMember.id, name = findMember.name)
    }

    @GetMapping("/api/v1/members")
    fun memberListV1(): List<Member> {
        return memberService.findMembers()
    }

    @GetMapping("/api/v2/members")
    fun memberListV2(): ApiResponse<List<MemberDto>> {
        return ApiResponse(memberService.findMembers().map { m -> MemberDto(m.name) }.toList())
    }

    data class MemberDto(val name: String)

    data class UpdateMemberResponse(val id: Long, val name: String)

    data class UpdateMemberRequest(val name: String)

    data class CreateMemberRequest(val name: String)

    data class CreateMemberResponse(val id: Long)
}