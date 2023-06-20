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

    fun getCarts(userId: Long): List<CartResponse> {
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
}
