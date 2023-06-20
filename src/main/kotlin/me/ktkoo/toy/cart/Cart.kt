package me.ktkoo.toy.cart

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import me.ktkoo.toy.product.Product
import me.ktkoo.toy.user.User

@Entity
class Cart(
    @Id
    @GeneratedValue()
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
