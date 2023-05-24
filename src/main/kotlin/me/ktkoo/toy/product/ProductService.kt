package me.ktkoo.toy.product

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(private val productRepository: ProductRepository) {

    fun createProduct(productDto: ProductDto) {
        val newProduct = Product(
            name = productDto.name,
            price = productDto.price,
            stockQuantity = productDto.stockQuantity,
        )
        productRepository.save(newProduct)
    }

    @Transactional(readOnly = true)
    fun findById(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseThrow { NoSuchElementException("Product with id: $productId not found.") }
    }
}
