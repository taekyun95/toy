package me.ktkoo.toy.interfaces.auth

import me.ktkoo.toy.domain.jwt.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auths")
class TokenApiController(
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping
    fun authenticateAndGetToken(@RequestBody request: TokenDto.TokenRequest): String {
        val username = request.username
        val password = request.password
        val token = UsernamePasswordAuthenticationToken(username, password)
        val authentication: Authentication = authenticationManager.authenticate(token)
        if (authentication.isAuthenticated) {
            return jwtService.generateToken(username)
        } else {
            throw UsernameNotFoundException("invalid user request !")
        }
    }
}
