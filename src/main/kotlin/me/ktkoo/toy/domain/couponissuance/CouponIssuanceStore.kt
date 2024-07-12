package me.ktkoo.toy.domain.couponissuance

interface CouponIssuanceStore {
    fun store(couponIssuance: CouponIssuance): CouponIssuance
}
