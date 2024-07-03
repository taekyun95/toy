package me.ktkoo.toy.domain.item

import me.ktkoo.toy.domain.item.ItemCommand.RegisterItemRequest
import me.ktkoo.toy.domain.partner.Partner
import me.ktkoo.toy.domain.partner.PartnerReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemServiceImpl(
    private val partnerReader: PartnerReader,
    private val itemStore: ItemStore,
    private val itemReader: ItemReader,
    private val itemOptionSeriesFactory: ItemOptionSeriesFactory
) : ItemService {

    @Transactional
    override fun registerItem(command: RegisterItemRequest, partnerToken: String): String {
        val partner: Partner = partnerReader.getPartner(partnerToken)
        val initItem = command.toEntity(partner.id!!)
        val item = itemStore.store(initItem)
        itemOptionSeriesFactory.store(command, item)
        return item.itemToken
    }

    @Transactional
    override fun changeOnSale(itemToken: String) {
        val item = itemReader.getItemBy(itemToken)
        item.changeOnSale()
    }

    @Transactional
    override fun changeEndOfSale(itemToken: String) {
        val item = itemReader.getItemBy(itemToken)
        item.changeEndOfSale()
    }

    @Transactional(readOnly = true)
    override fun retrieveItemInfo(itemToken: String): ItemInfo.Main {
        val item = itemReader.getItemBy(itemToken)
        val itemOptionGroupInfoList = itemReader.getItemOptionSeries(item)
        return ItemInfo.Main(item, itemOptionGroupInfoList)
    }
}
