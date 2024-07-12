package me.ktkoo.toy.infrastructure.coupon

import me.ktkoo.common.exception.EntityNotFoundException
import me.ktkoo.toy.domain.coupon.Coupon
import me.ktkoo.toy.domain.coupon.CouponRead
import org.springframework.stereotype.Component

@Component
class CouponReadImpl(private val couponRepository: CouponRepository) : CouponRead {
    override fun getCoupon(couponToken: String): Coupon {
        return couponRepository.findByCouponToken(couponToken) ?: throw EntityNotFoundException()
    }
}
