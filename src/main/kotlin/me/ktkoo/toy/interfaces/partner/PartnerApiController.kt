package me.ktkoo.toy.interfaces.partner

import jakarta.validation.Valid
import me.ktkoo.toy.application.partner.PartnerFacade
import me.ktkoo.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/partners")
class PartnerApiController(
    private val partnerFacade: PartnerFacade
) {

    @PostMapping
    fun registerPartner(@Valid @RequestBody request: PartnerDto.RegisterRequest): CommonResponse<*> {
        val command = request.toCommand()
        val partnerInfo = partnerFacade.registerPartner(command)
        val response = PartnerDto.RegisterResponse(partnerInfo)
        return CommonResponse.success(response)
    }
}
