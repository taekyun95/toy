package me.ktkoo.toy.cart

import me.ktkoo.toy.product.ProductService
import me.ktkoo.toy.user.UserService
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val userService: UserService,
    private val productService: ProductService,
) {
    fun createCart(cartRequest: CartRequest): Cart {
        Cart(
            user = userService.getUser(cartRequest.userId),
            product = productService.getProduct(cartRequest.productId),
            quantity = cartRequest.quantity,
        ).let {
            return cartRepository.save(it)
        }
    }
}
