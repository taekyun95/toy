package me.ktkoo.toy.domain.item

import me.ktkoo.toy.domain.item.ItemInfo.ItemOptionGroupInfo

interface ItemReader {
    fun getItemBy(itemToken: String): Item
    fun getItemOptionSeries(item: Item): List<ItemOptionGroupInfo>
}
