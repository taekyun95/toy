package me.ktkoo.toy.common.exception

import me.ktkoo.toy.common.response.ErrorCode


class EntityNotFoundException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER)

    constructor(message: String?) : super(message!!, ErrorCode.COMMON_INVALID_PARAMETER)
}

