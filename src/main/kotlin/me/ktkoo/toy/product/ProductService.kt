package me.ktkoo.toy.product

import me.ktkoo.toy.orderproduct.OrderProductDto
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(private val productRepository: ProductRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun createProduct(dto: RequestCreateProductDto): ProductResponseDto {
        val newProduct = Product(
            name = dto.name,
            price = dto.price,
            stockQuantity = dto.stockQuantity,
        )
        val savedProduct = productRepository.save(newProduct)
        logger.info("Created new product: $savedProduct")
        return ProductResponseDto.fromEntity(savedProduct)
    }

    fun deductProductStockForOrder(dto: OrderProductDto): Product {
        val product = this.findProductById(dto.productId)
        product.deductProductStock(dto.stockQuantity)
        return product
    }

    @Transactional(readOnly = true)
    fun findProductById(productId: Long): Product {
        return productRepository.findById(productId)
            .orElseThrow { NoSuchElementException("Product with id: $productId not found.") }
    }

    @Transactional(readOnly = true)
    fun getProducts(pageable: Pageable): Page<Product> {
        return productRepository.findAll(pageable)
    }

    @Transactional(readOnly = true)
    fun getProduct(id: Long): ProductResponseDto {
        return productRepository.findById(id)
            .map { it.toResponseDto() }
            .orElseThrow { NoSuchElementException("Product with id: $id not found.") }
    }
}
