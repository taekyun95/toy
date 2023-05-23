package me.ktkoo.toy.product

import java.math.BigDecimal
import java.math.BigInteger

data class ProductDto(
    val name: String,
    val price: BigDecimal,
    val count: BigInteger,
)
