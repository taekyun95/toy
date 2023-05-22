package me.ktkoo.toy.product

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(private val productRepository: ProductRepository) {
    @Transactional(readOnly = true)
    fun findById(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseThrow { NoSuchElementException("Product with id: $productId not found.") }
    }
}
