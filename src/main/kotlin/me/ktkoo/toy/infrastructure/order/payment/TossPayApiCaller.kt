package me.ktkoo.toy.infrastructure.order.payment

import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import me.ktkoo.toy.domain.order.payment.PayMethod
import org.springframework.stereotype.Component


@Component
class TossPayApiCaller : PaymentApiCaller {
    override fun support(payMethod: PayMethod): Boolean {
        return PayMethod.TOSS_PAY == payMethod
    }

    override fun pay(request: PaymentRequest) {
        // TODO - 구현
    }
}

