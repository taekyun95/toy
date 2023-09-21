package me.ktkoo.toy.config

import java.math.BigDecimal
import java.util.Random
import me.ktkoo.toy.order.Order
import me.ktkoo.toy.order.OrderRepository
import me.ktkoo.toy.orderproduct.OrderProduct
import me.ktkoo.toy.orderproduct.OrderProductDto
import me.ktkoo.toy.orderproduct.OrderProductRepository
import me.ktkoo.toy.product.Product
import me.ktkoo.toy.product.ProductRepository
import me.ktkoo.toy.product.ProductService
import me.ktkoo.toy.user.User
import me.ktkoo.toy.user.UserRepository
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DatabaseInitializer(
	private val productRepository: ProductRepository,
	private val orderRepository: OrderRepository,
	private val productService: ProductService,
	private val orderProductRepository: OrderProductRepository,
	private val userRepository: UserRepository,
) :
	CommandLineRunner {
	private val random = Random()
	private val logger = KotlinLogging.logger {}

	@Transactional
	override fun run(vararg args: String) {
		val productsToSave = (1..1000).map {
			Product(
				name = "이름$it",
				price = BigDecimal(10_00 * it),
				stockQuantity = (10 * it).toLong(),
			)
		}
		val savedProducts = productRepository.saveAll(productsToSave)

		var orderUserId = 1L
		val savedOrders = mutableListOf<Order>()
		val orderProductsToSave = mutableListOf<OrderProduct>()

		for ((index, product) in savedProducts.withIndex()) {
			val orderProductDtos = mutableListOf<OrderProductDto>()

			product.id?.let {
				orderProductDtos.add(
					OrderProductDto(
						productId = it,
						stockQuantity = random.nextLong(10),
					)
				)
			}

			if ((index + 1) % 3 == 0 || index == savedProducts.size - 1) {
				val order = Order(userId = orderUserId)
				val savedOrder = orderRepository.save(order)
				savedOrders.add(savedOrder)

				for (orderProductDto in orderProductDtos) {
					val retrievedProduct = productService.getProduct(orderProductDto.productId)
					retrievedProduct.order(orderProductDto.stockQuantity)

					val orderProduct = OrderProduct(
						order = savedOrder,
						product = retrievedProduct,
						stockQuantity = orderProductDto.stockQuantity,
						price = retrievedProduct.price,
					)
					orderProductsToSave.add(orderProduct)
				}

				// Reset for next order
				orderProductDtos.clear()
				// Rotate user ID between 1 and 10
				orderUserId = if (orderUserId < 10) orderUserId + 1 else 1
			}
		}

		orderProductRepository.saveAll(orderProductsToSave)
		logger.info { "Orders created: $savedOrders" }

		userRepository.save(User(email = "test@gmail.com", password = "1234", phoneNumber = "010-1234-5678"))
	}
}
