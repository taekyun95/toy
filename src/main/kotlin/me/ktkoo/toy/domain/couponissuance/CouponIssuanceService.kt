package me.ktkoo.toy.domain.couponissuance

import me.ktkoo.toy.domain.coupon.CouponInfo

interface CouponIssuanceService {
    fun registerIssuanceCoupon(coupon: CouponInfo.Main, userId: Long): String?
}
