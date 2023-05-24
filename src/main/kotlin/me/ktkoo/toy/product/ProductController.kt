package me.ktkoo.toy.product

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping
    fun newProduct(
        @Valid @RequestBody
        productDto: ProductDto,
    ): ResponseEntity<Void> {
        productService.createProduct(productDto)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
