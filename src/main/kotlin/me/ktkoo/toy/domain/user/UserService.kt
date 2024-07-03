package me.ktkoo.toy.domain.user

import me.ktkoo.toy.interfaces.user.UserDto
import me.ktkoo.toy.interfaces.user.UserUpdateDto

interface UserService {
    fun store(command: UserCommand.RegisterUser): String
    fun getUser(userToken: String): UserInfo.Main
}
