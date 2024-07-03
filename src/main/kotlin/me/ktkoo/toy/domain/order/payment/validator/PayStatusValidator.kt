package me.ktkoo.toy.domain.order.payment.validator

import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest

import org.springframework.stereotype.Component


@org.springframework.core.annotation.Order(value = 3)
@Component
class PayStatusValidator : PaymentValidator {
    override fun validate(order: Order, paymentRequest: PaymentRequest) {
        if (order.isAlreadyPaymentComplete()) throw InvalidParamException("이미 결제완료된 주문입니다")
    }
}

