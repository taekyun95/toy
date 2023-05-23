package me.ktkoo.toy.product

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/product")
class ProductController {

    @PostMapping
    fun newProduct(productDto: ProductDto) {
    }
}
