package me.ktkoo.toy.handler

import etc.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Internal Server Error", ex.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        return badRequest(ex)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleIllegalArgumentException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
        return badRequest(ex)
    }

    private fun badRequest(ex: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse("Bad Request", ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}
