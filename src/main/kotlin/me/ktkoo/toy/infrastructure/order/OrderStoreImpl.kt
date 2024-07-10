package me.ktkoo.toy.infrastructure.order

import me.ktkoo.toy.domain.order.item.OrderItemOption
import me.ktkoo.toy.domain.order.Order
import me.ktkoo.toy.domain.order.OrderRepository
import me.ktkoo.toy.domain.order.OrderStore
import me.ktkoo.toy.domain.order.item.OrderItem
import me.ktkoo.toy.domain.order.item.OrderItemOptionGroup
import org.springframework.stereotype.Component


@Component
class OrderStoreImpl(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val orderItemOptionGroupRepository: OrderItemOptionGroupRepository,
    private val orderItemOptionRepository: OrderItemOptionRepository,
) : OrderStore {


    override fun store(order: Order): Order {
        return orderRepository.save(order)
    }

    override fun store(orderItem: OrderItem): OrderItem {
        return orderItemRepository.save(orderItem)
    }

    override fun store(orderItemOptionGroup: OrderItemOptionGroup): OrderItemOptionGroup {
        return orderItemOptionGroupRepository.save(orderItemOptionGroup)
    }

    override fun store(orderItemOption: OrderItemOption): OrderItemOption {
        return orderItemOptionRepository.save(orderItemOption)
    }
}
