package me.ktkoo.toy.infrastructure.order

import dev.practice.order.domain.order.item.OrderItemOption
import java.util.stream.Collectors
import me.ktkoo.toy.domain.item.ItemReader
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderCommand.RegisterOrder
import me.ktkoo.toy.domain.order.OrderItemSeriesFactory
import me.ktkoo.toy.domain.order.OrderStore
import me.ktkoo.toy.domain.order.item.OrderItem
import me.ktkoo.toy.domain.order.item.OrderItemOptionGroup
import org.springframework.stereotype.Component


@Component
class OrderItemSeriesFactoryImpl(
    private val itemReader: ItemReader,
    private val orderStore: OrderStore
) : OrderItemSeriesFactory {

    override fun store(order: Order, requestOrder: RegisterOrder): List<OrderItem> {
        return requestOrder.orderItemList.stream()
            .map { orderItemRequest ->
                val item = itemReader.getItemBy(orderItemRequest.itemToken)
                val initOrderItem: OrderItem = orderItemRequest.toEntity(order, item)
                val orderItem: OrderItem = orderStore.store(initOrderItem)

                orderItemRequest.orderItemOptionGroupList.forEach { orderItemOptionGroupRequest ->
                    val initOrderItemOptionGroup: OrderItemOptionGroup = orderItemOptionGroupRequest.toEntity(orderItem)
                    val orderItemOptionGroup: OrderItemOptionGroup = orderStore.store(initOrderItemOptionGroup)
                    orderItemOptionGroupRequest.orderItemOptionList.forEach { orderItemOptionRequest ->
                        val initOrderItemOption: OrderItemOption = orderItemOptionRequest.toEntity(orderItemOptionGroup)
                        orderStore.store(initOrderItemOption)
                    }
                }
                orderItem
            }.collect(Collectors.toList())
    }
}
