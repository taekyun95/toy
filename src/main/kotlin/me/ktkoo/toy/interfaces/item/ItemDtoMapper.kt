package me.ktkoo.toy.interfaces.item

import me.ktkoo.toy.domain.item.ItemCommand
import me.ktkoo.toy.domain.item.ItemInfo
import org.mapstruct.*


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
interface ItemDtoMapper {
    // register
    @Mappings(Mapping(source = "request.itemOptionGroupList", target = "itemOptionGroupRequestList"))
    fun of(request: ItemDto.RegisterItemRequest): ItemCommand.RegisterItemRequest

    @Mappings(Mapping(source = "itemOptionList", target = "itemOptionRequestList"))
    fun of(request: ItemDto.RegisterItemOptionGroupRequest): ItemCommand.RegisterItemOptionGroupRequest

    fun of(request: ItemDto.RegisterItemOptionRequest): ItemCommand.RegisterItemOptionRequest

    fun of(itemToken: String): ItemDto.RegisterResponse

    // retrieve
    fun of(main: ItemInfo.Main): ItemDto.Main

    fun of(itemOptionGroup: ItemInfo.ItemOptionGroupInfo): ItemDto.ItemOptionGroupInfo

    fun of(itemOption: ItemInfo.ItemOptionInfo): ItemDto.ItemOptionInfo
}

