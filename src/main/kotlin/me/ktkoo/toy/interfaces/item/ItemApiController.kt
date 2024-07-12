package me.ktkoo.toy.interfaces.item

import jakarta.validation.Valid
import me.ktkoo.common.response.CommonResponse
import me.ktkoo.toy.application.item.ItemFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/items")
class ItemApiController(
    private val itemFacade: ItemFacade,
    private val itemDtoMapper: ItemDtoMapper,
) {

    @PostMapping
    fun registerItem(@Valid @RequestBody request: ItemDto.RegisterItemRequest): CommonResponse<*> {
        val partnerToken: String = request.partnerToken
        val itemCommand = itemDtoMapper.of(request)
        val itemToken = itemFacade.registerItem(itemCommand, partnerToken)
        val response = itemDtoMapper.of(itemToken)
        return CommonResponse.success(response)
    }

    @PostMapping("/change-on-sales")
    fun changeOnSaleItem(@RequestBody request: ItemDto.ChangeStatusItemRequest): CommonResponse<*> {
        val itemToken = request.itemToken
        itemFacade.changeOnSaleItem(itemToken)
        return CommonResponse.success("OK")
    }

    @PostMapping("/change-end-of-sales")
    fun changeEndOfSaleItem(@RequestBody request: ItemDto.ChangeStatusItemRequest): CommonResponse<*> {
        val itemToken = request.itemToken
        itemFacade.changeEndOfSaleItem(itemToken)
        return CommonResponse.success("OK")
    }

    @GetMapping("/{itemToken}")
    fun retrieve(@PathVariable("itemToken") itemToken: String): CommonResponse<*> {
        val itemInfo = itemFacade.retrieveItemInfo(itemToken)
        val response = itemDtoMapper.of(itemInfo)
        return CommonResponse.success(response)
    }
}

