package me.ktkoo.toy.infrastructure.couponissuance

import me.ktkoo.toy.domain.couponissuance.CouponIssuance
import me.ktkoo.toy.domain.couponissuance.CouponIssuanceStore
import org.springframework.stereotype.Component

@Component
class CouponIssuanceStoreImpl(private val couponIssuanceRepository: CouponIssuanceRepository) : CouponIssuanceStore {
    override fun store(couponIssuance: CouponIssuance): CouponIssuance {
        return couponIssuanceRepository.save(couponIssuance)
    }
}
