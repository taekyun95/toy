package me.ktkoo.toy.domain.coupon

class CouponCommand {
    class RegisterCoupon(val count: Long) {
        fun toEntity(): Coupon {
            return Coupon(count = this.count)
        }
    }

    class RegisterIssuanceCoupon(val couponToken: String, val userToken: String){

    }
}
