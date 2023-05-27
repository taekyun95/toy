package me.ktkoo.toy.handler

import me.ktkoo.toy.common.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): List<String> {
        val errors = ex.bindingResult.allErrors.mapNotNull { it.defaultMessage }

        logger.error("Validation error: $errors", ex)

        return errors
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception): ErrorResponse {
        logger.error("Internal Server Error: ", ex)
        return ErrorResponse("Internal Server Error", ex.message ?: "")
    }

    @ExceptionHandler(IllegalArgumentException::class, NoSuchElementException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(ex: Exception): ErrorResponse {
        logger.error("Bad Request: ", ex)
        return ErrorResponse("Bad Request", ex.message ?: "")
    }
}
