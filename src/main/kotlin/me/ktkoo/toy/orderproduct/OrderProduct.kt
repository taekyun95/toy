package me.ktkoo.toy.orderproduct

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import me.ktkoo.toy.order.Order
import me.ktkoo.toy.product.Product
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.cfg.AvailableSettings
import org.hibernate.id.enhanced.SequenceStyleGenerator

@Entity
class OrderProduct(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order-product-id-generator")
    @GenericGenerator(
        name = "order-product-id-generator",
        strategy = "sequence",
        parameters = [
            Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "hibernate_sequence"),
            Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
            Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lotl")
        ]
    )
    val id: Long? = null,

    @ManyToOne
    val order: Order,

    @ManyToOne
    val product: Product,

    val stockQuantity: Long,

    val price: BigDecimal,
)
