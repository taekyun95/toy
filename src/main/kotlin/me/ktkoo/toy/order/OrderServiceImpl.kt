package me.ktkoo.toy.order

import me.ktkoo.toy.orderproduct.OrderProductService
import me.ktkoo.toy.product.ProductService
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val productService: ProductService,
    private val orderProductService: OrderProductService,
) : OrderService {

    private val logger = KotlinLogging.logger {}

    @Transactional
    override fun createOrder(orderDto: OrderDto): Order {
        val order = Order(
            userId = orderDto.userId,
        )
        val savedOrder = orderRepository.save(order)
        logger.info { "Order created: $savedOrder" }

        orderDto.orderProduct.forEach {
            val product = productService.getProduct(it.productId)
            product.order(it.stockQuantity)
            /**
             * processOrderProduct를 OrderController에서 호출하지 않는 이유
             * Controller에 비즈니스 로직을 알고 있어야 하기 때문입니다.
             */
            orderProductService.processOrderProduct(it, savedOrder, product)
        }

        return savedOrder
    }

    @Transactional
    override fun updateOrderStatus(orderIds: List<Long>, status: OrderStatus): Int {
        return orderRepository.updateOrderStatus(orderIds, status)
    }

    override fun getOrders(userId: Long, pageable: Pageable): Page<List<Order>> {
        return orderRepository.findByUserId(userId, pageable)
    }
}
