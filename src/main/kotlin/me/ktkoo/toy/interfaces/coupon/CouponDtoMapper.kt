package me.ktkoo.toy.interfaces.coupon

import me.ktkoo.toy.domain.coupon.CouponCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
interface CouponDtoMapper {
    fun of(request: CouponDto.RegisterCouponRequest): CouponCommand.RegisterCoupon
    fun of(couponToken: String): CouponDto.RegisterCouponResponse
    fun of(couponToken: String, userToken: String): CouponCommand.RegisterIssuanceCoupon
}
