package me.ktkoo.toy.orderproduct

import me.ktkoo.toy.order.Order
import me.ktkoo.toy.product.Product
import me.ktkoo.toy.product.ProductDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderProductService(
    private val orderProductRepository: OrderProductRepository,
) {
    @Transactional
    fun processOrderProduct(productDto: ProductDto, order: Order, product: Product) {
        val orderProduct = OrderProduct(
            order = order,
            product = product,
            count = productDto.count,
            price = product.getPrice(),
        )
        orderProductRepository.save(orderProduct)
    }
}
