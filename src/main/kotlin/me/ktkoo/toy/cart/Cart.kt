package me.ktkoo.toy.cart

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import me.ktkoo.toy.product.Product
import me.ktkoo.toy.user.User
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import org.hibernate.cfg.AvailableSettings
import org.hibernate.id.enhanced.SequenceStyleGenerator

@Entity
class Cart(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart-id-generator")
    @GenericGenerator(
        name = "cart-id-generator",
        strategy = "sequence",
        parameters = [
            Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "hibernate_sequence"),
            Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1000"),
            Parameter(name = AvailableSettings.PREFERRED_POOLED_OPTIMIZER, value = "pooled-lotl")
        ]
    )
    var id: Long? = null,

    @ManyToOne
    val user: User,

    @ManyToOne
    val product: Product,

    var quantity: Int,
) {
    fun getId(): Long {
        return id ?: throw Exception("Cart id is null")
    }
}
