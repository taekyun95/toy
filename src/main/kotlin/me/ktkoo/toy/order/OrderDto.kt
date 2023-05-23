package me.ktkoo.toy.order

import jakarta.validation.constraints.Size
import me.ktkoo.toy.orderproduct.OrderProductDto

data class OrderDto(
    val userId: Long,
    @Size(min = 1)
    val orderProductDtos: List<OrderProductDto>,
)
