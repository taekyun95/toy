package me.ktkoo.toy.infrastructure.coupon

import me.ktkoo.toy.domain.coupon.Coupon
import org.springframework.data.jpa.repository.JpaRepository


interface CouponRepository : JpaRepository<Coupon, Long>{
    fun findByCouponToken(couponToken: String): Coupon?
}
