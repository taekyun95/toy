package me.ktkoo.toy.config

import java.math.BigDecimal
import java.util.Random
import me.ktkoo.toy.order.OrderDto
import me.ktkoo.toy.order.OrderService
import me.ktkoo.toy.orderproduct.OrderProductDto
import me.ktkoo.toy.product.Product
import me.ktkoo.toy.product.ProductRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DatabaseInitializer(private val productRepository: ProductRepository, private val orderService: OrderService) :
    CommandLineRunner {
    val random = Random()

    @Transactional
    override fun run(vararg args: String) {
        val products = (1..1000).map {
            productRepository.save(
                Product(
                    name = "이름$it",
                    price = BigDecimal(10_00 * it),
                    stockQuantity = (10 * it).toLong(),
                ),
            )
        }

        var orderUserId = 1L
        val orderProductDtos = mutableListOf<OrderProductDto>()

        for ((index, product) in products.withIndex()) {
            product.id?.let {
                orderProductDtos.add(
                    OrderProductDto(
                        productId = it,
                        stockQuantity = random.nextLong(10),
                    ),
                )
            }

            if ((index + 1) % 3 == 0 || index == products.size - 1) {
                val orderDto = OrderDto(
                    userId = orderUserId,
                    orderProduct = orderProductDtos,
                )
                orderService.createOrder(orderDto)
                // Reset for next order
                orderProductDtos.clear()
                // Rotate user ID between 1 and 10
                orderUserId = if (orderUserId < 10) orderUserId + 1 else 1
            }
        }
    }
}
