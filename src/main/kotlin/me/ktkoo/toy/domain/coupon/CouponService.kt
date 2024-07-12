package me.ktkoo.toy.domain.coupon

interface CouponService {
    fun registerCoupon(command: CouponCommand.RegisterCoupon): String
    fun getCoupon(couponToken: String): CouponInfo.Main
}
