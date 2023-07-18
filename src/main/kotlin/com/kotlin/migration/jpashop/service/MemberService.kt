package com.kotlin.migration.jpashop.service

import com.kotlin.migration.jpashop.domain.Member
import com.kotlin.migration.jpashop.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun join(member: Member): Long {
        validateDuplicateMember(member)
        return memberRepository.save(member).id
    }

    private fun validateDuplicateMember(member: Member) {
        val findByName: List<Member> = memberRepository.findByName(member.name)

        if (findByName.isNotEmpty()) {
            throw IllegalStateException("이미 존재하는 외원입니다")
        }
    }

    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(id: Long): Member {
        return memberRepository.findById(id).orElseThrow { RuntimeException("Member not found") }
    }

    @Transactional
    fun update(id: Long, name: String) {
        val member = memberRepository.findById(id).orElseThrow { RuntimeException("Member not found") }
        member.update(name)
    }
}