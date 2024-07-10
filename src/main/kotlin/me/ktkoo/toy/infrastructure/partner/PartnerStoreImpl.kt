package me.ktkoo.toy.infrastructure.partner

import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.partner.Partner
import me.ktkoo.toy.domain.partner.PartnerStore
import org.springframework.stereotype.Component


@Component
class PartnerStoreImpl(
    private val partnerRepository: PartnerRepository
) : PartnerStore {

    override fun store(initPartner: Partner): Partner {
        if (initPartner.partnerToken.isEmpty()) throw InvalidParamException("partner.getPartnerToken()")
        if (initPartner.partnerName.isEmpty()) throw InvalidParamException("partner.getPartnerName()")
        if (initPartner.businessNo.isEmpty()) throw InvalidParamException("partner.getBusinessNo()")
        if (initPartner.email.isEmpty()) throw InvalidParamException("partner.getEmail()")

        return partnerRepository.save(initPartner)
    }
}
