package me.ktkoo.toy.domain.partner

class PartnerInfo(partner: Partner) {
    val id: Long = partner.id!!
    val partnerToken: String = partner.partnerToken
    val partnerName: String = partner.partnerName
    val businessNo: String = partner.businessNo
    val email: String = partner.email
    val status: Partner.Status = partner.status
}
