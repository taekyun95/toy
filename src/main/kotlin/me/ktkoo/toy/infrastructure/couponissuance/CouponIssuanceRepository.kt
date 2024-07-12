package me.ktkoo.toy.infrastructure.couponissuance

import me.ktkoo.toy.domain.couponissuance.CouponIssuance
import org.springframework.data.jpa.repository.JpaRepository

interface CouponIssuanceRepository : JpaRepository<CouponIssuance, Long> {
    fun countByCouponToken(couponToken: String): Long
}
