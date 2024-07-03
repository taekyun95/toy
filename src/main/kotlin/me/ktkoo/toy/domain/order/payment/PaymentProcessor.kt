package me.ktkoo.toy.domain.order.payment

import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest


interface PaymentProcessor {
    fun pay(order: Order, request: PaymentRequest)
}
