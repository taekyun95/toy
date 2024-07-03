package me.ktkoo.toy.domain.order.payment.validator

import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest


interface PaymentValidator {
    fun validate(order: Order, paymentRequest: PaymentRequest)
}
