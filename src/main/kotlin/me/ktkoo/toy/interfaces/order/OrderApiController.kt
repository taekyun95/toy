package me.ktkoo.toy.interfaces.order

import jakarta.validation.Valid
import me.ktkoo.common.response.CommonResponse
import me.ktkoo.toy.application.order.OrderFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/orders")
class OrderApiController(
    private val orderFacade: OrderFacade,
    private val orderDtoMapper: OrderDtoMapper,
) {


    @PostMapping("/init")
    fun registerOrder(@Valid @RequestBody request: OrderDto.RegisterOrderRequest): CommonResponse<*> {
        val orderCommand = orderDtoMapper.of(request)
        val orderToken = orderFacade.registerOrder(orderCommand)
        val response = orderDtoMapper.of(orderToken)
        return CommonResponse.success(response)
    }

    @GetMapping("/{orderToken}")
    fun retrieveOrder(@PathVariable orderToken: String): CommonResponse<*> {
        val orderResult = orderFacade.retrieveOrder(orderToken)
        val response = orderDtoMapper.of(orderResult)
        return CommonResponse.success(response)
    }

    @PostMapping("/payment-order")
    fun paymentOrder(@Valid @RequestBody paymentRequest: OrderDto.PaymentRequest): CommonResponse<*> {
        val paymentCommand = orderDtoMapper.of(paymentRequest)
        orderFacade.paymentOrder(paymentCommand)
        return CommonResponse.success("OK")
    }
}

