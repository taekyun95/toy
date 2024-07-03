package me.ktkoo.toy.domain.order

import me.ktkoo.toy.domain.order.OrderCommand.PaymentRequest
import me.ktkoo.toy.domain.order.OrderCommand.RegisterOrder
import me.ktkoo.toy.domain.order.payment.PaymentProcessor
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class OrderServiceImpl(
    private val orderStore: OrderStore,
    private val orderReader: OrderReader,
    private val orderItemSeriesFactory: OrderItemSeriesFactory,
    private val paymentProcessor: PaymentProcessor,
) : OrderService {

    private val orderInfoMapper: OrderInfoMapper = Mappers.getMapper(OrderInfoMapper::class.java)

    @Transactional
    override fun registerOrder(registerOrder: RegisterOrder): String {
        val order = orderStore.store(registerOrder.toEntity())
        orderItemSeriesFactory.store(order, registerOrder)
        return order.orderToken
    }

    @Transactional
    override fun paymentOrder(paymentRequest: PaymentRequest) {
        val orderToken: String = paymentRequest.orderToken
        val order = orderReader.getOrder(orderToken)
        paymentProcessor.pay(order, paymentRequest)
        order.orderComplete()
    }

    @Transactional(readOnly = true)
    override fun retrieveOrder(orderToken: String): OrderInfo.Main {
        val order = orderReader.getOrder(orderToken)
        val orderItemList = order.orderItemList
        return orderInfoMapper.of(order, orderItemList)
    }
}
