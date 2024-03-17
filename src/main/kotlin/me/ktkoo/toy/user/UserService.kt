package me.ktkoo.toy.user

interface UserService {
    fun createUser(userDto: UserDto): User
    fun updateUser(id: Long, userUpdateDto: UserUpdateDto): User
    fun getUser(id: Long): User
    fun getUserByUsername(username: String): User
}
