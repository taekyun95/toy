package me.ktkoo.toy.domain.item

import me.ktkoo.toy.domain.item.ItemCommand.RegisterItemRequest

interface ItemService {
    fun registerItem(command: RegisterItemRequest, partnerToken: String): String
    fun changeOnSale(itemToken: String)
    fun changeEndOfSale(itemToken: String)
    fun retrieveItemInfo(itemToken: String): ItemInfo.Main
}
