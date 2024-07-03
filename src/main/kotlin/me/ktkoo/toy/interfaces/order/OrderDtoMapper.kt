package me.ktkoo.toy.interfaces.order

import me.ktkoo.toy.domain.order.OrderCommand.*
import me.ktkoo.toy.domain.order.OrderInfo
import org.mapstruct.*


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
interface OrderDtoMapper {
    @Mappings(Mapping(source = "orderedAt", target = "orderedAt", dateFormat = "yyyy-MM-dd HH:mm:ss"))
    fun of(mainResult: OrderInfo.Main): OrderDto.Main

    fun of(deliveryResult: OrderInfo.DeliveryInfo): OrderDto.DeliveryInfo

    fun of(orderItemResult: OrderInfo.OrderItem): OrderDto.OrderItem

    fun of(request: OrderDto.RegisterOrderRequest): RegisterOrder

    fun of(request: OrderDto.RegisterOrderItem): RegisterOrderItem

    fun of(request: OrderDto.RegisterOrderItemOptionGroupRequest): RegisterOrderItemOptionGroup

    fun of(request: OrderDto.RegisterOrderItemOptionRequest): RegisterOrderItemOption

    fun of(orderToken: String): OrderDto.RegisterResponse

    fun of(request: OrderDto.PaymentRequest): PaymentRequest
}
