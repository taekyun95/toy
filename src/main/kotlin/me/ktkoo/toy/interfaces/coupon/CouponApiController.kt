package me.ktkoo.toy.interfaces.coupon

import me.ktkoo.common.response.CommonResponse
import me.ktkoo.common.response.ErrorCode
import me.ktkoo.toy.application.coupon.CouponFacade
import me.ktkoo.toy.domain.auth.UserDetailsImpl
import org.springframework.security.core.annotation.CurrentSecurityContext
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/coupons")
class CouponApiController(
    private val couponFacade: CouponFacade,
    private val mapper: CouponDtoMapper,
) {

    @PostMapping
    fun registerCoupon(@RequestBody request: CouponDto.RegisterCouponRequest): CommonResponse<String> {
        val command = mapper.of(request)
        val couponToken = couponFacade.registerCoupon(command)
        return CommonResponse.success(couponToken)
    }

    @PostMapping("/{couponToken}/issuance")
    fun issuanceCoupon(@PathVariable couponToken: String, @CurrentSecurityContext userInfoDetail: UserDetailsImpl): CommonResponse<out Any> {
        val issuanceCouponToken = couponFacade.issuanceCoupon(couponToken, userInfoDetail.userId)
        if (issuanceCouponToken == null) {
            return CommonResponse.fail(ErrorCode.COUPONS_ISSUED_HAS_BEEN_EXCEEDED.errorMsg, ErrorCode.COUPONS_ISSUED_HAS_BEEN_EXCEEDED.name)
        } else {
            return CommonResponse.success(issuanceCouponToken)
        }
    }
}
