package me.ktkoo.toy.product

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class ProductDto(
    @NotBlank
    val name: String,

    @NotNull
    @Min(0)
    val price: BigDecimal,

    @NotNull
    @Min(0)
    val stockQuantity: Long,
)
