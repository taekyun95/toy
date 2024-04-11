package me.ktkoo.toy.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch


@Aspect
@Component
class LoggingAspect {

    companion object {
        private val log = LoggerFactory.getLogger(LoggingAspect::class.java)
        private const val EXECUTION_TIME_THRESHOLD_MS = 10
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    fun serviceLayerExecution() {}

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    fun repositoryLayerExecution() {}

    @Before("serviceLayerExecution()")
    fun logBefore(joinPoint: JoinPoint) {
        log.info("Enter: {}.{}() with arguments = {}", joinPoint.signature.declaringTypeName, joinPoint.signature.name, joinPoint.args.joinToString())
    }

    @AfterThrowing(pointcut = "serviceLayerExecution()", throwing = "e")
    fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {
        log.error("Exception in {}.{}() with arguments = {} and cause = '{}'", joinPoint.signature.declaringTypeName, joinPoint.signature.name, joinPoint.args.joinToString(), e.message, e)
    }

    @Around("serviceLayerExecution() || repositoryLayerExecution()")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val stopWatch = StopWatch()
        stopWatch.start()
        try {
            return joinPoint.proceed()
        } finally {
            stopWatch.stop()
            if (stopWatch.totalTimeMillis > EXECUTION_TIME_THRESHOLD_MS) {
                log.info("Execution time of {}.{}() : {} ms", joinPoint.signature.declaringTypeName, joinPoint.signature.name, stopWatch.totalTimeMillis)
            }
        }
    }
}
