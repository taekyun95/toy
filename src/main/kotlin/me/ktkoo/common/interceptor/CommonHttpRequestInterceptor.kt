package me.ktkoo.common.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.UUID

import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor


/*
이 인터셉터는 주로 로깅과 모니터링 목적으로 사용될 수 있습니다.
각 요청에 고유한 ID를 할당함으로써, 로그 파일에서 특정 요청의 처리 과정을 쉽게 추적하고 문제를 진단할 수 있게 도와줍니다.
또한, 요청 처리 과정에서 발생할 수 있는 이슈의 원인을 더 빠르고 정확하게 파악할 수 있게 해 줍니다.
 */
@Component
class CommonHttpRequestInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var requestEventId = request.getHeader(HEADER_REQUEST_UUID_KEY)
        if (requestEventId.isEmpty()) {
            requestEventId = UUID.randomUUID().toString()
        }

        MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId)
        return true
    }

    companion object {
        const val HEADER_REQUEST_UUID_KEY: String = "x-request-id"
    }
}
