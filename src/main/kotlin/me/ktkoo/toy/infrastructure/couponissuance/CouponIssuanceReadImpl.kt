package me.ktkoo.toy.infrastructure.couponissuance

import me.ktkoo.toy.domain.couponissuance.CouponIssuanceRead
import org.springframework.stereotype.Component

@Component
class CouponIssuanceReadImpl(private val couponIssuanceRepository: CouponIssuanceRepository) : CouponIssuanceRead {
    override fun getCount(couponToken: String): Long {
        return couponIssuanceRepository.countByCouponToken(couponToken)
    }
}
