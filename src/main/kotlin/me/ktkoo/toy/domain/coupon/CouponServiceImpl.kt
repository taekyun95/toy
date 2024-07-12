package me.ktkoo.toy.domain.coupon

import org.springframework.stereotype.Service

@Service
class CouponServiceImpl(
    private val couponStore: CouponStore,
    private val couponRead: CouponRead,
    private val mapper: CouponInfoMapper
) : CouponService {
    override fun registerCoupon(command: CouponCommand.RegisterCoupon): String {
        val coupon = command.toEntity()
        val newCoupon = couponStore.store(coupon)
        return newCoupon.couponToken
    }

    override fun getCoupon(couponToken: String): CouponInfo.Main {
        val coupon = couponRead.getCoupon(couponToken)
        return mapper.of(coupon)
    }
}
