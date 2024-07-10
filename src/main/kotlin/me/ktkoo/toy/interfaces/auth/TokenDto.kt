package me.ktkoo.toy.interfaces.auth

import jakarta.validation.constraints.NotEmpty

class TokenDto {
    class TokenRequest(
        @NotEmpty
        val username: String,
        @NotEmpty
        val password: String
    )
}
