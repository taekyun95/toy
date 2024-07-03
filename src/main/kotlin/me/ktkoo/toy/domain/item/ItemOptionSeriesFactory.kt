package me.ktkoo.toy.domain.item

import me.ktkoo.toy.domain.item.ItemCommand.RegisterItemRequest
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup


interface ItemOptionSeriesFactory {
    fun store(command: RegisterItemRequest, item: Item): List<ItemOptionGroup>
}
