package me.ktkoo.common.exception

import me.ktkoo.common.response.ErrorCode


class IllegalStatusException : BaseException {
    constructor() : super(ErrorCode.COMMON_ILLEGAL_STATUS)

    constructor(message: String) : super(message, ErrorCode.COMMON_ILLEGAL_STATUS)
}
