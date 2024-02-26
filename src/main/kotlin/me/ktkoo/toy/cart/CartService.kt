package me.ktkoo.toy.cart

import me.ktkoo.toy.product.ProductService
import me.ktkoo.toy.user.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class CartService(
    private val cartRepository: CartRepository,
    private val userService: UserService,
    private val productService: ProductService,
) {

    fun createCart(cartRequest: CartRequest): Cart {
        val user = userService.getUser(cartRequest.userId)
        val product = productService.getProduct(cartRequest.productId)
        if (product.stockQuantity < cartRequest.quantity) throw IllegalArgumentException("Requested quantity exceeds stock quantity.")

        return Cart(
            user = user,
            product = product,
            quantity = cartRequest.quantity,
        ).let {
            cartRepository.save(it)
        }
    }

    @Transactional(readOnly = true)
    open fun getCarts(userId: Long): List<CartResponse> {
        return cartRepository.findByUser_Id(userId).map {
            CartResponse(
                id = it.getId(),
                userId = userId,
                productId = it.product.getId(),
                productName = it.product.name,
                productPrice = it.product.price,
                productStockQuantity = it.product.stockQuantity,
                productStatus = it.product.status,
                quantity = it.quantity,
            )
        }
    }

    fun deleteCart(id: Long) {
        val cart = cartRepository.findById(id).orElseThrow { NoSuchElementException("Cart with id: $id not found.") }
        cartRepository.delete(cart)
    }

    fun updateCart(id: Long, quantity: Int): Cart {
        val cartItem = cartRepository.findById(id).orElseThrow { NoSuchElementException("Cart not found with ID: $id") }
        if (cartItem.product.stockQuantity < quantity) throw IllegalArgumentException("Requested quantity exceeds stock quantity.")
        cartItem.quantity = quantity
        return cartRepository.save(cartItem)
    }
}
