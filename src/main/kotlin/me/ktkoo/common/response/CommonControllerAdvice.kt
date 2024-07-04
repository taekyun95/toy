package me.ktkoo.common.response

import me.ktkoo.common.exception.BaseException
import me.ktkoo.common.interceptor.CommonHttpRequestInterceptor
import org.apache.catalina.connector.ClientAbortException
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.core.NestedExceptionUtils
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CommonControllerAdvice {

    companion object {
        private val log = LoggerFactory.getLogger(CommonControllerAdvice::class.java)
        private val SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST = ErrorCode.values()
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun onException(e: Exception): CommonResponse<Unit> {
        val requestId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.error("[Exception] eventId = {}, error = {}", requestId, e.message, e)
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException::class)
    fun onBaseException(e: BaseException): CommonResponse<Unit> {
        val requestId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        if (SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST.contains(e.errorCode)) {
            log.error("[BaseException] eventId = {}, cause = {}, errorMsg = {}", requestId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).message)
        } else {
            log.warn("[BaseException] eventId = {}, cause = {}, errorMsg = {}", requestId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).message)
        }
        return CommonResponse.fail(e.message ?: "", e.errorCode.name)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ClientAbortException::class)
    fun skipException(e: Exception): CommonResponse<Unit> {
        val requestId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn("[SkipException] eventId = {}, cause = {}, errorMsg = {}", requestId, NestedExceptionUtils.getMostSpecificCause(e), NestedExceptionUtils.getMostSpecificCause(e).message)
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): CommonResponse<Unit> {
        val requestId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn("[MethodArgumentNotValidException] eventId = {}, errorMsg = {}", requestId, NestedExceptionUtils.getMostSpecificCause(e).message)
        val bindingResult = e.bindingResult
        val fe = bindingResult.fieldError
        if (fe != null) {
            val message = "Request Error ${fe.field}=${fe.rejectedValue} (${fe.defaultMessage})"
            return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name)
        } else {
            return CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER.errorMsg, ErrorCode.COMMON_INVALID_PARAMETER.name)
        }
    }
}
