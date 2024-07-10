package me.ktkoo.common.response

data class CommonResponse<T>(
    val result: Result,
    val data: T? = null,
    val message: String?,
    val errorCode: String? = null
) {
    companion object {
        fun <T> success(data: T, message: String = ""): CommonResponse<T> =
            CommonResponse(Result.SUCCESS, data, message)

        fun fail(message: String, errorCode: String): CommonResponse<Unit> =
            CommonResponse(Result.FAIL, message = message, errorCode = errorCode)

        fun fail(errorCode: ErrorCode): CommonResponse<Unit> =
            CommonResponse(Result.FAIL, message = errorCode.getErrorMsg(), errorCode = errorCode.name)
    }

    enum class Result {
        SUCCESS, FAIL
    }
}
