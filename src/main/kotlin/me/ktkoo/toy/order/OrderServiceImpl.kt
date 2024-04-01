package me.ktkoo.toy.order

import me.ktkoo.toy.orderproduct.OrderProductDto
import me.ktkoo.toy.orderproduct.OrderProductService
import me.ktkoo.toy.user.UserService
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderProductService: OrderProductService,
    private val userService: UserService
) : OrderService {

    private val logger = KotlinLogging.logger {}

    @Transactional
    override fun createOrder(dto: OrderProductDto, username: String): Order {

        val order = Order(user = userService.getUserByUsername(username))
        val savedOrder = orderRepository.save(order)
        logger.info { "Order created: $savedOrder" }

        orderProductService.addProductToOrder(dto, savedOrder)

        return savedOrder
    }

    @Transactional
    override fun createMultipleOrders(dtos: List<OrderProductDto>, username: String): Order {
        val order = Order(user = userService.getUserByUsername(username))
        val savedOrder = orderRepository.save(order)
        logger.info { "Order created: $savedOrder" }
        orderProductService.addProductsToOrder(dtos, savedOrder)

        return savedOrder
    }

    @Transactional
    override fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int {
        return orderRepository.updateOrderStatus(orderIds, status)
    }

    override fun getOrders(userId: Long, pageable: Pageable): Page<List<Order>> {
        return orderRepository.findByUserId(userId, pageable)
    }

    override fun getOrders(username: String, pageable: Pageable): Page<List<Order>> {
        val user = userService.getUserByUsername(username)
        return orderRepository.findByUser(user, pageable)
    }
}
