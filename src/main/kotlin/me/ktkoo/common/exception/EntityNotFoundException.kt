package me.ktkoo.common.exception

import me.ktkoo.common.response.ErrorCode


class EntityNotFoundException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER)

    constructor(message: String?) : super(message!!, ErrorCode.COMMON_INVALID_PARAMETER)
}

