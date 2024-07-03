package me.ktkoo.toy.interfaces.partner

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import me.ktkoo.toy.domain.partner.PartnerCommand
import me.ktkoo.toy.domain.partner.PartnerInfo


class PartnerDto {
    class RegisterRequest(
        @NotEmpty(message = "partnerName 는 필수값입니다")
        private val partnerName: String,

        @NotEmpty(message = "businessNo 는 필수값입니다")
        private val businessNo: String,

        @Email(message = "email 형식에 맞아야 합니다")
        @NotEmpty(message = "email 는 필수값입니다")
        private val email: String,
    ) {

        fun toCommand(): PartnerCommand {
            return PartnerCommand(
                partnerName = partnerName,
                businessNo = businessNo,
                email = email,
            )
        }
    }

    class RegisterResponse(partnerInfo: PartnerInfo) {
        private val partnerToken = partnerInfo.partnerToken
        private val partnerName = partnerInfo.partnerName
        private val businessNo = partnerInfo.businessNo
        private val email = partnerInfo.email
        private val status = partnerInfo.status
    }
}
