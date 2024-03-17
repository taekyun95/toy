package me.ktkoo.toy.order

import me.ktkoo.toy.auth.UserDetail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody orderDto: OrderDto, @AuthenticationPrincipal currentUserDetails: UserDetails): ResponseEntity<Order> {
        val username = (currentUserDetails as UserDetail).username
        val order = orderService.createOrder(orderDto, username)
        return ResponseEntity.ok(order)
    }

    @PatchMapping("/status")
    fun updateOrderStatus(@RequestParam orderIds: List<Long>, @RequestParam status: OrderStatus): ResponseEntity<String> {
        val updatedOrdersCount = orderService.updateOrderStatus(orderIds, status)
        return ResponseEntity.ok("$updatedOrdersCount orders have been marked as '${status.name}'.")
    }

    @GetMapping
    fun getOrders(@AuthenticationPrincipal currentUserDetails: UserDetails, pageable: Pageable): ResponseEntity<Page<List<Order>>> {
        val username = (currentUserDetails as UserDetail).username
        return ResponseEntity(orderService.getOrders(username, pageable), HttpStatus.OK)
    }
}
