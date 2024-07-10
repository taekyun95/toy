package me.ktkoo.toy.application.partner

import me.ktkoo.toy.domain.notification.NotificationService
import me.ktkoo.toy.domain.partner.PartnerCommand
import me.ktkoo.toy.domain.partner.PartnerInfo
import me.ktkoo.toy.domain.partner.PartnerService
import org.springframework.stereotype.Service


@Service
class PartnerFacade(
    private val partnerService: PartnerService,
    private val notificationService: NotificationService,
) {


    fun registerPartner(command: PartnerCommand): PartnerInfo {
        val partnerInfo = partnerService.registerPartner(command)
        notificationService.sendEmail(partnerInfo.email, "title", "description")
        return partnerInfo
    }
}
