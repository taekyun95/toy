package me.ktkoo.common.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch

@Aspect
@Component
class LoggingAspect {

    companion object {
        private val log = LoggerFactory.getLogger(LoggingAspect::class.java)
        private const val EXECUTION_TIME_THRESHOLD_MS = 10
    }

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    fun controllerLayerExecution() {}

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    fun serviceLayerExecution() {}

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    fun repositoryLayerExecution() {}

    @Before("controllerLayerExecution() || serviceLayerExecution() || repositoryLayerExecution()")
    fun logBefore(joinPoint: JoinPoint) {
        val requestId = MDC.get("x-request-id")
        log.info("[Before] Enter: {}.{}() with arguments = {} [requestID={}]", joinPoint.signature.declaringTypeName, joinPoint.signature.name, joinPoint.args.joinToString(), requestId)
    }

    @AfterReturning(pointcut = "controllerLayerExecution() || serviceLayerExecution() || repositoryLayerExecution()", returning = "result")
    fun logAfterReturning(joinPoint: JoinPoint, result: Any?) {
        val requestId = MDC.get("x-request-id")
        log.info("[AfterReturning] Exit: {}.{}() with result = {} [requestID={}]", joinPoint.signature.declaringTypeName, joinPoint.signature.name, result, requestId)
    }

    @Around("controllerLayerExecution() || serviceLayerExecution() || repositoryLayerExecution()")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val stopWatch = StopWatch()
        stopWatch.start()
        try {
            return joinPoint.proceed()
        } finally {
            stopWatch.stop()
            if (stopWatch.totalTimeMillis > EXECUTION_TIME_THRESHOLD_MS) {
                val requestId = MDC.get("x-request-id")
                log.info("[ExecutionTime] {}.{}() executed in {} ms [requestID={}]", joinPoint.signature.declaringTypeName, joinPoint.signature.name, stopWatch.totalTimeMillis, requestId)
            }
        }
    }
}
