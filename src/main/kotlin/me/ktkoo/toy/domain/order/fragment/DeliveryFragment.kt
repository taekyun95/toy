package me.ktkoo.toy.domain.order.fragment

import me.ktkoo.common.exception.InvalidParamException

class DeliveryFragment(
    private val receiverName: String,
    private val receiverPhone: String,
    private val receiverZipcode: String,
    private val receiverAddress1: String,
    private val receiverAddress2: String,
    private val etcMessage: String
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
