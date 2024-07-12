package me.ktkoo.toy.interfaces.coupon

class CouponDto {
    class RegisterCouponRequest(val count: Long)
    class RegisterCouponResponse(val couponToken: String)
}
