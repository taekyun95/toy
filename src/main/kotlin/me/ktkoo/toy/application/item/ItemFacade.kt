package me.ktkoo.toy.application.item

import me.ktkoo.toy.domain.item.ItemCommand.RegisterItemRequest
import me.ktkoo.toy.domain.item.ItemInfo
import me.ktkoo.toy.domain.item.ItemService
import me.ktkoo.toy.domain.notification.NotificationService
import org.springframework.stereotype.Service


@Service
class ItemFacade(
    private val itemService: ItemService,
    private val notificationService: NotificationService,
) {

    fun registerItem(request: RegisterItemRequest, partnerToken: String): String {
        val itemToken = itemService.registerItem(request, partnerToken)
        notificationService.sendEmail(null, null, null)
        return itemToken
    }

    fun changeOnSaleItem(itemToken: String) {
        itemService.changeOnSale(itemToken)
    }

    fun changeEndOfSaleItem(itemToken: String) {
        itemService.changeEndOfSale(itemToken)
    }

    fun retrieveItemInfo(itemToken: String): ItemInfo.Main {
        return itemService.retrieveItemInfo(itemToken)
    }
}
