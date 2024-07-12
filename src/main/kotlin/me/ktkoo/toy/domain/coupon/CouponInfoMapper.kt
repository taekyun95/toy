package me.ktkoo.toy.domain.coupon

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
interface CouponInfoMapper {

    @Mapping(source = "coupon.id", target = "couponId")
    fun of(coupon: Coupon): CouponInfo.Main
}
