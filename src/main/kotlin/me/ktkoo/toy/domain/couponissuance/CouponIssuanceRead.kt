package me.ktkoo.toy.domain.couponissuance

interface CouponIssuanceRead {
    fun getCount(couponToken: String): Long
}
