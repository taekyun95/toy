package me.ktkoo.toy.application.order

import me.ktkoo.toy.domain.notification.NotificationService
import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import me.ktkoo.toy.domain.order.OrderCommand.RegisterOrder
import me.ktkoo.toy.domain.order.OrderInfo
import me.ktkoo.toy.domain.order.OrderService
import org.springframework.stereotype.Service

@Service
class OrderFacade(
    private val orderService: OrderService,
    private val notificationService: NotificationService,
) {
    fun registerOrder(registerOrder: RegisterOrder): String {
        val orderToken = orderService.registerOrder(registerOrder)
        notificationService.sendKakao("ORDER_COMPLETE", "content")
        return orderToken
    }

    fun retrieveOrder(orderToken: String): OrderInfo.Main {
        return orderService.retrieveOrder(orderToken)
    }

    fun paymentOrder(paymentRequest: PaymentRequest) {
        orderService.paymentOrder(paymentRequest)
        notificationService.sendKakao(null, null)
    }
}
