package me.ktkoo.toy.domain.order

import me.ktkoo.toy.domain.order.item.OrderItemOption
import me.ktkoo.toy.domain.order.item.OrderItem
import me.ktkoo.toy.domain.order.item.OrderItemOptionGroup
import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface OrderInfoMapper {

    @Mappings(
        Mapping(source = "order.id", target = "orderId"),
        Mapping(source = "order.deliveryFragment", target = "deliveryInfo"),
        Mapping(expression = "java(order.calculateTotalAmount())", target = "totalAmount"),
        Mapping(expression = "java(order.status.name())", target = "status"),
        Mapping(expression = "java(order.status.getDescription())", target = "statusDescription")
    )
    fun of(order: Order, orderItemList: List<OrderItem>): OrderInfo.Main
    @Mappings(
        Mapping(expression = "java(orderItem.deliveryStatus.name)", target = "deliveryStatus"),
        Mapping(expression = "java(orderItem.deliveryStatus.description)", target = "deliveryStatusDescription"),
        Mapping(expression = "java(orderItem.calculateTotalAmount())", target = "totalAmount")
    )
    fun of(orderItem: OrderItem): OrderInfo.OrderItem

    fun of(orderItemOptionGroup: OrderItemOptionGroup): OrderInfo.OrderItemOptionGroup

    fun of(orderItemOption: OrderItemOption): OrderInfo.OrderItemOption
}

