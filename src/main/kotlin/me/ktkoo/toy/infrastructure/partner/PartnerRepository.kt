package me.ktkoo.toy.infrastructure.partner

import me.ktkoo.toy.domain.partner.Partner
import org.springframework.data.jpa.repository.JpaRepository


interface PartnerRepository : JpaRepository<Partner, Long> {
    fun findByPartnerToken(partnerToken: String): Partner?
}
