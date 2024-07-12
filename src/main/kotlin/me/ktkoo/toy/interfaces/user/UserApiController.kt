package me.ktkoo.toy.interfaces.user

import me.ktkoo.common.response.CommonResponse
import me.ktkoo.toy.application.user.UserFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserApiController(
    private val userDtoMapper: UserDtoMapper,
    private val userFacade: UserFacade
) {

    @PostMapping
    fun createUser(@RequestBody request: UserDto.RegisterUserRequest): CommonResponse<UserDto.RegisterResponse> {
        val command = userDtoMapper.of(request)
        val userToken = userFacade.registerUser(command)
        val response = userDtoMapper.of(userToken)
        return CommonResponse.success(response)
    }

    @GetMapping("/{userToken}")
    fun getUser(@PathVariable userToken: String): ResponseEntity<Any> {
        return try {
            val user = userFacade.getUser(userToken)
            ResponseEntity.ok(user)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
