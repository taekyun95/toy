package me.ktkoo.toy.infrastructure.item.optiongroup

import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroupStore
import org.springframework.stereotype.Component


@Component
class ItemOptionGroupStoreImpl(
    private val itemOptionGroupRepository: ItemOptionGroupRepository
) : ItemOptionGroupStore {

    override fun store(itemOptionGroup: ItemOptionGroup): ItemOptionGroup {
        return itemOptionGroupRepository.save(itemOptionGroup)
    }
}
