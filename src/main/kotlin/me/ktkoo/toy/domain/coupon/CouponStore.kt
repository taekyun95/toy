package me.ktkoo.toy.domain.coupon

interface CouponStore {
    fun store(coupon: Coupon): Coupon
}
