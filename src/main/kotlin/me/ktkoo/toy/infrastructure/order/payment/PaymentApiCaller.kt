package me.ktkoo.toy.infrastructure.order.payment

import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import me.ktkoo.toy.domain.order.payment.PayMethod


interface PaymentApiCaller {
    fun support(payMethod: PayMethod): Boolean
    fun pay(request: PaymentRequest)
}
