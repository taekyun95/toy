package me.ktkoo.toy.orderproduct

import me.ktkoo.toy.order.Order
import me.ktkoo.toy.product.Product
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class OrderProductService(
    private val orderProductRepository: OrderProductRepository,
) {
    @Transactional
    open fun processOrderProduct(orderProductDto: OrderProductDto, order: Order, product: Product) {
        val orderProduct = OrderProduct(
            order = order,
            product = product,
            stockQuantity = orderProductDto.stockQuantity,
            price = product.price,
        )
        orderProductRepository.save(orderProduct)
    }
}
