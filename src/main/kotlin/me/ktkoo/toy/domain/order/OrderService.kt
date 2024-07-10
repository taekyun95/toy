package me.ktkoo.toy.domain.order

import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import me.ktkoo.toy.domain.order.OrderCommand.RegisterOrder


interface OrderService {
    fun registerOrder(registerOrder: RegisterOrder): String

    fun paymentOrder(paymentRequest: PaymentRequest)

    fun retrieveOrder(orderToken: String): OrderInfo.Main
}
