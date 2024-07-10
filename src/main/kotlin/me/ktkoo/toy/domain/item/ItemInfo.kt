package me.ktkoo.toy.domain.item

import java.math.BigDecimal
import me.ktkoo.toy.domain.item.option.ItemOption
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup

class ItemInfo {
    data class Main(
        val itemToken: String,
        val partnerId: Long,
        val itemName: String,
        val itemPrice: BigDecimal,
        val status: Item.Status,
        val itemOptionGroupList: List<ItemOptionGroupInfo>
    ) {
        constructor(item: Item, itemOptionGroupList: List<ItemOptionGroupInfo>) : this(
            itemToken = item.itemToken,
            partnerId = item.partnerId,
            itemName = item.itemName,
            itemPrice = item.itemPrice,
            status = item.status,
            itemOptionGroupList = itemOptionGroupList

        )
    }

    data class ItemOptionGroupInfo(
        val ordering: Int,
        val itemOptionGroupName: String,
        val itemOptionList: List<ItemOptionInfo>
    ) {
        constructor(itemOptionGroup: ItemOptionGroup, itemOptionList: List<ItemOptionInfo>) : this(
            ordering = itemOptionGroup.ordering,
            itemOptionGroupName = itemOptionGroup.itemOptionGroupName,
            itemOptionList = itemOptionList
        )
    }

    data class ItemOptionInfo(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long
    ) {
        constructor(itemOption: ItemOption) : this(
            ordering = itemOption.ordering,
            itemOptionName = itemOption.itemOptionName,
            itemOptionPrice = itemOption.itemOptionPrice
        )
    }
}
