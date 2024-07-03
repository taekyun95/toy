package me.ktkoo.toy.infrastructure.item

import me.ktkoo.common.exception.InvalidParamException
import me.ktkoo.toy.domain.item.Item
import me.ktkoo.toy.domain.item.ItemStore
import org.springframework.stereotype.Component


@Component
class ItemStoreImpl(private val itemRepository: ItemRepository) : ItemStore {

    override fun store(initItem: Item): Item {
        validCheck(initItem)
        return itemRepository.save(initItem)
    }

    private fun validCheck(item: Item) {
        if (item.itemToken.isEmpty()) throw InvalidParamException("Item.itemToken")
        if (item.itemName.isEmpty()) throw InvalidParamException("Item.itemName")
    }
}
