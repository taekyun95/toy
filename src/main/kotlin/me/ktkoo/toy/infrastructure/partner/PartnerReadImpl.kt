package me.ktkoo.toy.infrastructure.partner

import me.ktkoo.common.exception.EntityNotFoundException
import me.ktkoo.toy.domain.partner.Partner
import me.ktkoo.toy.domain.partner.PartnerReader
import org.springframework.stereotype.Component


@Component
class PartnerReadImpl(
    private val partnerRepository: PartnerRepository
) : PartnerReader {

    override fun getPartner(partnerId: Long): Partner {
        return partnerRepository.findById(partnerId)
            .orElseThrow { EntityNotFoundException() }
    }

    override fun getPartner(partnerToken: String): Partner {
        return partnerRepository.findByPartnerToken(partnerToken)
            ?: throw EntityNotFoundException()
    }
}
