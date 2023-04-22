package me.ktkoo.toy.order

import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(private val orderRepository: OrderRepository) : OrderService {

    private val logger = KotlinLogging.logger {}

    override fun createOrder(orderDto: OrderDto): Order {
        val order = Order(
            userId = orderDto.userId,
            status = orderDto.status,
        )
        val savedOrder = orderRepository.save(order)
        logger.info { "Order created: $savedOrder" }

        return savedOrder
    }

    @Transactional
    override fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int {
        return orderRepository.updateOrderStatus(orderIds, status)
    }
}
