package me.ktkoo.toy.user

data class UserDto(
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
){
    var encodingPassword: String = ""
}
