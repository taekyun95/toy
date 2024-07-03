package me.ktkoo.toy.application.user

import me.ktkoo.toy.domain.user.User
import me.ktkoo.toy.domain.user.UserCommand
import me.ktkoo.toy.domain.user.UserService
import me.ktkoo.toy.interfaces.user.UserUpdateDto
import org.springframework.stereotype.Service

@Service
class UserFacade(private val userService: UserService) {
    fun registerUser(command: UserCommand.RegisterUser): String {
        return userService.store(command)
    }

    fun updateUserInfo(userToken: String, userUpdateDto: UserUpdateDto) {
        TODO("Not yet implemented")
    }
}
