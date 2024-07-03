package me.ktkoo.toy.domain.order.payment.validator

import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import org.springframework.stereotype.Component

@org.springframework.core.annotation.Order(value = 1)
@Component
class PayAmountValidator : PaymentValidator {
    override fun validate(order: Order, paymentRequest: PaymentRequest) {
        if (order.calculateTotalAmount() != paymentRequest.amount) {
            throw InvalidParamException("주문가격이 불일치합니다")
        }
    }
}
