package me.ktkoo.toy.cart

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/carts")
class CartController(private val cartService: CartService) {

    @PostMapping
    fun createCart(@RequestBody cartRequest: CartRequest): ResponseEntity<Cart> {
        return ResponseEntity(cartService.createCart(cartRequest), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateCart(@PathVariable id: Long, @RequestParam quantity: Int): ResponseEntity<Cart> {
        val updatedCartItem = cartService.updateCart(id, quantity)
        return ResponseEntity.ok(updatedCartItem)
    }

    @DeleteMapping("/{id}")
    fun deleteCart(@PathVariable id: Long): ResponseEntity<Void> {
        cartService.deleteCart(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("/{userId}")
    fun getCarts(@PathVariable userId: Long): ResponseEntity<List<CartResponse>> {
        return ResponseEntity(cartService.getCarts(userId), HttpStatus.OK)
    }
}
