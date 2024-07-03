package me.ktkoo.toy.domain.order

import java.time.ZonedDateTime


class OrderInfo {
    class Main(
        private val orderId: Long,
        private val orderToken: String,
        private val userId: Long,
        private val payMethod: String,
        private val totalAmount: Long,
        private val deliveryInfo: DeliveryInfo,
        private val orderedAt: ZonedDateTime,
        private val status: String,
        private val statusDescription: String,
        private val orderItemList: List<OrderItem>,
    )

    class DeliveryInfo(
        private val receiverName: String,
        private val receiverPhone: String,
        private val receiverZipcode: String,
        private val receiverAddress1: String,
        private val receiverAddress2: String,
        private val etcMessage: String,
    )

    class OrderItem(
        private val orderCount: Int,
        private val partnerId: Long,
        private val itemId: Long,
        private val itemName: String,
        private val totalAmount: Long,
        private val itemPrice: Long,
        private val deliveryStatus: String,
        private val deliveryStatusDescription: String,
        private val orderItemOptionGroupList: List<OrderItemOptionGroup>,
    )

    class OrderItemOptionGroup(
        private val ordering: Int,
        private val itemOptionGroupName: String,
        private val orderItemOptionList: List<OrderItemOption>,
    )

    class OrderItemOption(
        private val ordering: Int,
        private val itemOptionName: String,
        private val itemOptionPrice: Long,
    )
}
