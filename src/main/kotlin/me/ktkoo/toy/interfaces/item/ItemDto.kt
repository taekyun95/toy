package me.ktkoo.toy.interfaces.item

import me.ktkoo.toy.domain.item.Item


class ItemDto {
    class RegisterItemRequest(
        val partnerToken: String,
        val itemName: String,
        val itemPrice: Long,
        val itemOptionGroupList: List<RegisterItemOptionGroupRequest> = emptyList(),
    )

    class RegisterItemOptionGroupRequest(
        val ordering: Int,
        val itemOptionGroupName: String,
        val itemOptionList: List<RegisterItemOptionRequest> = emptyList(),
    )

    class RegisterItemOptionRequest(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long,
    )

    class RegisterResponse(
        val itemToken: String
    )

    class ChangeStatusItemRequest(
        val itemToken: String
    )

    class Main(
        val itemToken: String,
        val partnerId: Long,
        val itemName: String,
        val itemPrice: Long,
        val status: Item.Status,
        val itemOptionGroupList: List<ItemOptionGroupInfo>,
    )

    class ItemOptionGroupInfo(
        val ordering: Int,
        val itemOptionGroupName: String,
        val itemOptionList: List<ItemOptionInfo>,
    )

    class ItemOptionInfo(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long,
    )
}

