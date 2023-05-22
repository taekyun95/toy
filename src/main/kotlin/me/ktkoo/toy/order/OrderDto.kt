package me.ktkoo.toy.order

import jakarta.validation.constraints.Size
import me.ktkoo.toy.product.ProductDto

data class OrderDto(
    val userId: Long,
    @Size(min = 1)
    val productDtos: List<ProductDto>,
)
