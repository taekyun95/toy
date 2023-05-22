package me.ktkoo.toy.orderproduct

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.math.BigInteger
import me.ktkoo.toy.order.Order
import me.ktkoo.toy.product.Product

@Entity
data class OrderProduct(

    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    val order: Order,

    @ManyToOne
    val product: Product,

    val count: BigInteger,

    val price: BigDecimal,
)
