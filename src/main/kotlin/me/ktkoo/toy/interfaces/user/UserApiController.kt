package me.ktkoo.toy.interfaces.user

import me.ktkoo.common.response.CommonResponse
import me.ktkoo.toy.application.user.UserFacade
import me.ktkoo.toy.domain.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserApiController(
    private val userService: UserService,
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

    @PutMapping("/{userToken}")
    fun updateUser(
        @PathVariable userToken: String,
        @RequestBody userUpdateDto: UserUpdateDto,
    ): CommonResponse<String> {
        val command = userDtoMapper.of(userUpdateDto)
        userFacade.updateUserInfo(userToken, userUpdateDto)
        return CommonResponse.success("OK")
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val user = userService.getUser(id)
            ResponseEntity.ok(user)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
