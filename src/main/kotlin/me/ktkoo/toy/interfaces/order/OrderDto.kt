package me.ktkoo.toy.interfaces.order

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.ktkoo.toy.domain.order.payment.PayMethod


class OrderDto {
    class RegisterOrderRequest(
        @NotNull(message = "userId 는 필수값입니다")
        private val userId: Long,

        @NotBlank(message = "payMethod 는 필수값입니다")
        private val payMethod: String,

        @NotBlank(message = "receiverName 는 필수값입니다")
        private val receiverName: String,

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        private val receiverPhone: String,

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        private val receiverZipcode: String,

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        private val receiverAddress1: String,

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        private val receiverAddress2: String,

        @NotBlank(message = "etcMessage 는 필수값입니다")
        private val etcMessage: String,

        private val orderItemList: List<RegisterOrderItem>,
    )

    class RegisterOrderItem(
        @NotNull(message = "orderCount 는 필수값입니다")
        private val orderCount: Int,

        @NotBlank(message = "itemToken 는 필수값입니다")
        private val itemToken: String,

        @NotBlank(message = "itemName 는 필수값입니다")
        private val itemName: String,

        @NotNull(message = "itemPrice 는 필수값입니다")
        private val itemPrice: Long,

        private val orderItemOptionGroupList: List<RegisterOrderItemOptionGroupRequest>,
    )

    class RegisterOrderItemOptionGroupRequest(
        @NotNull(message = "ordering 는 필수값입니다")
        private val ordering: Int,

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        private val itemOptionGroupName: String,

        private val orderItemOptionList: List<RegisterOrderItemOptionRequest>,
    )

    class RegisterOrderItemOptionRequest(
        @NotNull(message = "ordering 는 필수값입니다")
        private val ordering: Int,

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        private val itemOptionName: String,

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        private val itemOptionPrice: Long,
    )

    class RegisterResponse(
        private val orderToken: String,
    )

    class PaymentRequest(
        @NotBlank(message = "orderToken 는 필수값입니다")
        private val orderToken: String,

        @NotNull(message = "payMethod 는 필수값입니다")
        private val payMethod: PayMethod,

        @NotNull(message = "amount 는 필수값입니다")
        private val amount: Long,

        @NotBlank(message = "orderDescription 는 필수값입니다")
        private val orderDescription: String,
    )


    // 조회
    class Main(
        private val orderToken: String,
        private val userId: Long,
        private val payMethod: String,
        private val totalAmount: Long,
        private val deliveryInfo: DeliveryInfo,
        private val orderedAt: String,
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
