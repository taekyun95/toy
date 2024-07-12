package me.ktkoo.toy.infrastructure.coupon

import me.ktkoo.toy.domain.coupon.Coupon
import me.ktkoo.toy.domain.coupon.CouponStore
import org.springframework.stereotype.Component

@Component
class CouponStoreImpl(private val couponRepository: CouponRepository) : CouponStore {
    override fun store(coupon: Coupon): Coupon {
        return couponRepository.save(coupon)
    }
}
