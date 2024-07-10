package me.ktkoo.toy.infrastructure.order

import me.ktkoo.common.exception.EntityNotFoundException
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderReader
import me.ktkoo.toy.domain.order.OrderRepository
import org.springframework.stereotype.Component


@Component
class OrderReaderImpl(private val orderRepository: OrderRepository) : OrderReader {

    override fun getOrder(orderToken: String): Order {
        return orderRepository.findByOrderToken(orderToken)
            ?: throw EntityNotFoundException()
    }
}
