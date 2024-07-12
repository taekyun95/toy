package me.ktkoo.toy.application.coupon

import me.ktkoo.toy.domain.coupon.CouponCommand
import me.ktkoo.toy.domain.coupon.CouponService
import me.ktkoo.toy.domain.couponissuance.CouponIssuanceService
import org.springframework.stereotype.Service

@Service
class CouponFacade(
    private val couponService: CouponService,
    private val couponIssuanceService: CouponIssuanceService
) {
    fun registerCoupon(command: CouponCommand.RegisterCoupon): String {
        return couponService.registerCoupon(command)
    }

    fun issuanceCoupon(couponToken: String, userId: Long): String? {
        val coupon = couponService.getCoupon(couponToken)
        return couponIssuanceService.registerIssuanceCoupon(coupon, userId)
    }
}
