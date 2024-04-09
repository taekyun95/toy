package me.ktkoo.toy.product

import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/products")
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping
    fun createProduct(
        @Valid @RequestBody
        dto: RequestCreateProductDto,
    ): ResponseEntity<ProductResponseDto> {
        val productResponse = productService.createProduct(dto)
        return ResponseEntity(productResponse, HttpStatus.CREATED)
    }

    @GetMapping
    fun getProducts(pageable: Pageable): ResponseEntity<Page<Product>> {
        return ResponseEntity(productService.getProducts(pageable), HttpStatus.OK)
    }

    @GetMapping("{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductResponseDto> {
        return ResponseEntity(productService.getProduct(id), HttpStatus.OK)
    }
}
