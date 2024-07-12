package me.ktkoo.toy.domain.coupon

interface CouponRead {
    fun getCoupon(couponToken: String): Coupon
}
