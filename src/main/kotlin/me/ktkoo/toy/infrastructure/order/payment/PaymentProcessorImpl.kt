package me.ktkoo.toy.infrastructure.order.payment

import java.util.function.Consumer
import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import me.ktkoo.toy.domain.order.payment.PaymentProcessor
import me.ktkoo.toy.domain.order.payment.validator.PaymentValidator
import org.springframework.stereotype.Component


@Component
class PaymentProcessorImpl(
    private val paymentValidatorList: List<PaymentValidator>,
    private val paymentApiCallerList: List<PaymentApiCaller>
) : PaymentProcessor {


    override fun pay(order: Order, request: PaymentRequest) {
        paymentValidatorList.forEach(Consumer { paymentValidator: PaymentValidator ->
            paymentValidator.validate(
                order,
                request
            )
        })
        val payApiCaller = routingApiCaller(request)
        payApiCaller.pay(request)
    }

    private fun routingApiCaller(request: PaymentRequest): PaymentApiCaller {
        return paymentApiCallerList.stream()
            .filter { paymentApiCaller: PaymentApiCaller -> paymentApiCaller.support(request.payMethod) }
            .findFirst()
            .orElseThrow { InvalidParamException() }
    }
}
