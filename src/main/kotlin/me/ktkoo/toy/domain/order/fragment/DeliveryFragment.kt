package me.ktkoo.toy.domain.order.fragment

import jakarta.persistence.Embeddable
import me.ktkoo.common.exception.InvalidParamException

@Embeddable
class DeliveryFragment(
    val receiverName: String,
    val receiverPhone: String,
    val receiverZipcode: String,
    val receiverAddress1: String,
    val receiverAddress2: String,
    val etcMessage: String
) {
    init {
        validateParam(receiverName, "DeliveryFragment receiverName must not be empty.")
        validateParam(receiverPhone, "DeliveryFragment receiverPhone must not be empty.")
        validateParam(receiverZipcode, "DeliveryFragment receiverZipcode must not be empty.")
        validateParam(receiverAddress1, "DeliveryFragment receiverAddress1 must not be empty.")
        validateParam(receiverAddress2, "DeliveryFragment receiverAddress2 must not be empty.")
        validateParam(etcMessage, "DeliveryFragment etcMessage must not be empty.")
    }

    private fun validateParam(param: String, paramName: String) {
        if (param.isEmpty()) {
            throw InvalidParamException("DeliveryFragment $paramName must not be empty.")
        }
    }
}
