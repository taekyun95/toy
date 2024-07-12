package me.ktkoo.toy.domain.couponissuance

import me.ktkoo.toy.domain.coupon.CouponInfo
import org.springframework.stereotype.Service

@Service
class CouponIssuanceServiceImpl(
    private val couponIssuanceStore: CouponIssuanceStore,
    private val couponIssuanceRead: CouponIssuanceRead
) : CouponIssuanceService {
    override fun registerIssuanceCoupon(coupon: CouponInfo.Main, userId: Long): String? {
        val currentCount = couponIssuanceRead.getCount(coupon.couponToken)
        if (currentCount < coupon.count) {
            val couponIssuance = CouponIssuance(
                couponId = coupon.couponId,
                couponToken = coupon.couponToken,
                userId = userId
            )
            val newCouponIssuance = couponIssuanceStore.store(couponIssuance)
            return newCouponIssuance.couponIssuanceToken
        } else {
            return null
        }
    }
}
