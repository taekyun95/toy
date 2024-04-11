package me.ktkoo.toy.orderproduct

import me.ktkoo.toy.order.Order
import me.ktkoo.toy.product.ProductService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderProductService(
    private val orderProductRepository: OrderProductRepository,
    private val productService: ProductService
) {

    fun addProductToOrder(dto: OrderProductDto, newOrder: Order) {
        val product = productService.deductProductStockForOrder(dto)

        val orderProduct = OrderProduct(
            order = newOrder,
            product = product,
            stockQuantity = dto.stockQuantity,
            price = product.price,
        )
        orderProductRepository.save(orderProduct)
    }

    fun addProductsToOrder(dtos: List<OrderProductDto>, newOrder: Order) {
        val orderProducts = dtos.map { dto ->
            val product = productService.deductProductStockForOrder(dto)
            OrderProduct(
                order = newOrder,
                product = product,
                stockQuantity = dto.stockQuantity,
                price = product.price,
            )
        }
        orderProductRepository.saveAll(orderProducts)
    }
}
