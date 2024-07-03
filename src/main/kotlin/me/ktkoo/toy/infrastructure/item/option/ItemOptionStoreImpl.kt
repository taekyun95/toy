package me.ktkoo.toy.infrastructure.item.option

import me.ktkoo.toy.domain.item.option.ItemOption
import me.ktkoo.toy.domain.item.option.ItemOptionStore
import org.springframework.stereotype.Component


@Component
class ItemOptionStoreImpl(
    private val itemOptionRepository: ItemOptionRepository
) : ItemOptionStore {

    override fun store(itemOption: ItemOption) {
        itemOptionRepository.save(itemOption)
    }
}
