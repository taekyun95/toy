package me.ktkoo.toy.interfaces.order

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.ktkoo.toy.domain.order.payment.PayMethod


class OrderDto {
    class RegisterOrderRequest(
        @NotNull(message = "userId 는 필수값입니다")
        val userId: Long,

        @NotBlank(message = "payMethod 는 필수값입니다")
        val payMethod: String,

        @NotBlank(message = "receiverName 는 필수값입니다")
        val receiverName: String,

        @NotBlank(message = "receiverPhone 는 필수값입니다")
        val receiverPhone: String,

        @NotBlank(message = "receiverZipcode 는 필수값입니다")
        val receiverZipcode: String,

        @NotBlank(message = "receiverAddress1 는 필수값입니다")
        val receiverAddress1: String,

        @NotBlank(message = "receiverAddress2 는 필수값입니다")
        val receiverAddress2: String,

        @NotBlank(message = "etcMessage 는 필수값입니다")
        val etcMessage: String,

        val orderItemList: List<RegisterOrderItem>,
    )

    class RegisterOrderItem(
        @NotNull(message = "orderCount 는 필수값입니다")
        val orderCount: Int,

        @NotBlank(message = "itemToken 는 필수값입니다")
        val itemToken: String,

        @NotBlank(message = "itemName 는 필수값입니다")
        val itemName: String,

        @NotNull(message = "itemPrice 는 필수값입니다")
        val itemPrice: Long,

        val orderItemOptionGroupList: List<RegisterOrderItemOptionGroupRequest>,
    )

    class RegisterOrderItemOptionGroupRequest(
        @NotNull(message = "ordering 는 필수값입니다")
        val ordering: Int,

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        val itemOptionGroupName: String,

        val orderItemOptionList: List<RegisterOrderItemOptionRequest>,
    )

    class RegisterOrderItemOptionRequest(
        @NotNull(message = "ordering 는 필수값입니다")
        val ordering: Int,

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        val itemOptionName: String,

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        val itemOptionPrice: Long,
    )

    class RegisterResponse(
        val orderToken: String,
    )

    class PaymentRequest(
        @NotBlank(message = "orderToken 는 필수값입니다")
        val orderToken: String,

        @NotNull(message = "payMethod 는 필수값입니다")
        val payMethod: PayMethod,

        @NotNull(message = "amount 는 필수값입니다")
        val amount: Long,

        @NotBlank(message = "orderDescription 는 필수값입니다")
        val orderDescription: String,
    )


    // 조회
    class Main(
        val orderToken: String,
        val userId: Long,
        val payMethod: String,
        val totalAmount: Long,
        val deliveryInfo: DeliveryInfo,
        val orderedAt: String,
        val status: String,
        val statusDescription: String,
        val orderItemList: List<OrderItem>,
    )

    class DeliveryInfo(
        val receiverName: String,
        val receiverPhone: String,
        val receiverZipcode: String,
        val receiverAddress1: String,
        val receiverAddress2: String,
        val etcMessage: String,
    )

    class OrderItem(
        val orderCount: Int,
        val partnerId: Long,
        val itemId: Long,
        val itemName: String,
        val totalAmount: Long,
        val itemPrice: Long,
        val deliveryStatus: String,
        val deliveryStatusDescription: String,
        val orderItemOptionGroupList: List<OrderItemOptionGroup>,
    )

    class OrderItemOptionGroup(
        val ordering: Int,
        val itemOptionGroupName: String,
        val orderItemOptionList: List<OrderItemOption>,
    )

    class OrderItemOption(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long,
    )
}
