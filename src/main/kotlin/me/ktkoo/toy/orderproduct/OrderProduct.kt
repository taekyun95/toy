package me.ktkoo.toy.orderproduct

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import me.ktkoo.toy.common.BaseEntity
import me.ktkoo.toy.order.Order
import me.ktkoo.toy.product.Product

@Entity
class OrderProduct(

    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    val order: Order,

    @ManyToOne
    val product: Product,

    val stockQuantity: Long,

    val price: BigDecimal,
) : BaseEntity()
