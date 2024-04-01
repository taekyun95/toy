package me.ktkoo.toy.order

import me.ktkoo.toy.auth.UserDetail
import me.ktkoo.toy.orderproduct.OrderProductDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/newOrder")
    fun newOrder(@RequestBody dto: OrderProductDto, @AuthenticationPrincipal currentUserDetails: UserDetails): ResponseEntity<Order> {
        val username = (currentUserDetails as UserDetail).username
        val order = orderService.createOrder(dto, username)
        return ResponseEntity.ok(order)
    }

    @PostMapping("/newMultiOrder")
    fun newMultiOrder(
        @RequestBody dtos: List<OrderProductDto>,
        @AuthenticationPrincipal currentUserDetails: UserDetails
    ): ResponseEntity<Order> {
        val username = (currentUserDetails as UserDetail).username
        val order = orderService.createMultipleOrders(dtos, username)
        return ResponseEntity.ok(order)
    }

    @GetMapping
    fun getOrders(@AuthenticationPrincipal currentUserDetails: UserDetails, pageable: Pageable): ResponseEntity<Page<List<Order>>> {
        val username = (currentUserDetails as UserDetail).username
        val orders = orderService.getOrders(username, pageable)
        return ResponseEntity.ok(orders)
    }
}
