package me.ktkoo.toy.domain.item

import java.math.BigDecimal
import me.ktkoo.toy.domain.item.option.ItemOption
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup


class ItemCommand {
    class RegisterItemRequest(
        val itemName: String,
        val itemPrice: BigDecimal,
        // ex) 색상, 사이즈
        val itemOptionGroupRequestList: List<RegisterItemOptionGroupRequest> = listOf()
    ) {
        fun toEntity(partnerId: Long): Item {
            return Item(
                partnerId = partnerId,
                itemName = itemName,
                itemPrice = itemPrice,
            )
        }
    }

    class RegisterItemOptionGroupRequest(
        // ex) 색상
        val ordering: Int,
        val itemOptionGroupName: String,
        // ex) R, B, W
        val itemOptionRequestList: List<RegisterItemOptionRequest> = listOf()
    ) {
        fun toEntity(item: Item): ItemOptionGroup {
            return ItemOptionGroup(
                item = item,
                ordering = ordering,
                itemOptionGroupName = itemOptionGroupName
            )
        }
    }

    class RegisterItemOptionRequest(
        private val ordering: Int,
        private val itemOptionName: String,
        private val itemOptionPrice: Long
    ) {

        fun toEntity(itemOptionGroup: ItemOptionGroup): ItemOption {
            return ItemOption(
                itemOptionGroup = itemOptionGroup,
                ordering = ordering,
                itemOptionName = itemOptionName,
                itemOptionPrice = itemOptionPrice
            )

        }
    }
}
