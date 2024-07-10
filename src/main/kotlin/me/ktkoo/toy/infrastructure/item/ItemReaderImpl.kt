package me.ktkoo.toy.infrastructure.item

import java.util.stream.Collectors
import me.ktkoo.common.exception.EntityNotFoundException
import me.ktkoo.toy.domain.item.Item
import me.ktkoo.toy.domain.item.ItemInfo
import me.ktkoo.toy.domain.item.ItemInfo.ItemOptionGroupInfo
import me.ktkoo.toy.domain.item.ItemReader
import me.ktkoo.toy.domain.item.option.ItemOption
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup
import org.springframework.stereotype.Component


@Component
class ItemReaderImpl(
    private val itemRepository: ItemRepository
) : ItemReader {

    override fun getItemBy(itemToken: String): Item {
        return itemRepository.findByItemToken(itemToken) ?: throw EntityNotFoundException()
    }

    override fun getItemOptionSeries(item: Item): List<ItemOptionGroupInfo> {
        val itemOptionGroupList: List<ItemOptionGroup> = item.itemOptionGroupList
        return itemOptionGroupList.stream()
            .map { itemOptionGroup ->
                val itemOptionList: MutableList<ItemOption> = itemOptionGroup.itemOptionList
                val itemOptionInfoList: List<ItemInfo.ItemOptionInfo> = itemOptionList.stream()
                    .map { ItemInfo.ItemOptionInfo(it) }
                    .collect(Collectors.toList())
                ItemOptionGroupInfo(itemOptionGroup, itemOptionInfoList)
            }.collect(Collectors.toList())
    }
}
