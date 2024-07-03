package me.ktkoo.toy.infrastructure.item

import java.util.*
import java.util.stream.Collectors
import me.ktkoo.toy.domain.item.Item
import me.ktkoo.toy.domain.item.ItemCommand.RegisterItemOptionGroupRequest
import me.ktkoo.toy.domain.item.ItemCommand.RegisterItemRequest
import me.ktkoo.toy.domain.item.ItemOptionSeriesFactory
import me.ktkoo.toy.domain.item.option.ItemOption
import me.ktkoo.toy.domain.item.option.ItemOptionStore
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroup
import me.ktkoo.toy.domain.item.optiongroup.ItemOptionGroupStore
import org.springframework.stereotype.Component


@Component
class ItemOptionSeriesFactoryImpl(
    private val itemOptionGroupStore: ItemOptionGroupStore,
    private val itemOptionStore: ItemOptionStore,
) : ItemOptionSeriesFactory {


    override fun store(command: RegisterItemRequest, item: Item): List<ItemOptionGroup> {
        val itemOptionGroupRequestList: List<RegisterItemOptionGroupRequest> = command.itemOptionGroupRequestList
        if (itemOptionGroupRequestList.isEmpty()) return Collections.emptyList()

        return itemOptionGroupRequestList.stream()
            .map { requestItemOptionGroup ->
                // itemOptionGroup store
                val initItemOptionGroup: ItemOptionGroup = requestItemOptionGroup.toEntity(item)
                val itemOptionGroup = itemOptionGroupStore.store(initItemOptionGroup)

                // itemOption store
                requestItemOptionGroup.itemOptionRequestList.forEach { requestItemOption ->
                    val initItemOption: ItemOption = requestItemOption.toEntity(itemOptionGroup)
                    itemOptionStore.store(initItemOption)
                }
                itemOptionGroup
            }.collect(Collectors.toList())
    }
}
