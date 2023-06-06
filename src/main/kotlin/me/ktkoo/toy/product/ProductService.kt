package me.ktkoo.toy.product

import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(private val productRepository: ProductRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun createProduct(productDto: ProductDto): ProductResponseDto {
        val newProduct = Product(
            name = productDto.name,
            price = productDto.price,
            stockQuantity = productDto.stockQuantity,
        )
        val savedProduct = productRepository.save(newProduct)
        logger.info("Created new product: $savedProduct")
        return ProductResponseDto.fromEntity(savedProduct)
    }

    @Transactional(readOnly = true)
    fun findById(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseThrow { NoSuchElementException("Product with id: $productId not found.") }
    }

    @Transactional(readOnly = true)
    fun getProducts(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }
}
