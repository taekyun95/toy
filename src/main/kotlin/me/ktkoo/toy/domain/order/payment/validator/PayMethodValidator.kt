package me.ktkoo.toy.domain.order.payment.validator

import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import org.springframework.stereotype.Component


@Component
@org.springframework.core.annotation.Order(value = 2)
class PayMethodValidator : PaymentValidator {
    override fun validate(order: Order, paymentRequest: PaymentRequest) {
        if (order.payMethod != paymentRequest.payMethod.name) {
            throw InvalidParamException("주문 과정에서의 결제수단이 다릅니다.")
        }
    }
}
