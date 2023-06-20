package me.ktkoo.toy.cart

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/carts")
class CartController(private val cartService: CartService) {

    @PostMapping
    fun createCart(@RequestBody cartRequest: CartRequest): ResponseEntity<Cart> {
        return ResponseEntity(cartService.createCart(cartRequest), HttpStatus.CREATED)
    }

    @GetMapping("/{userId}")
    fun getCarts(@PathVariable userId: Long): ResponseEntity<List<CartResponse>> {
        return ResponseEntity(cartService.getCarts(userId), HttpStatus.OK)
    }
}
