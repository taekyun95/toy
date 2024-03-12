package me.ktkoo.toy.auth

import me.ktkoo.toy.jwt.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val jwtService: JwtService, private val authenticationManager: AuthenticationManager) {

    @PostMapping
    fun authenticateAndGetToken(@RequestBody authRequest: AuthRequest): String {
        val authentication: Authentication =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password))
        if (authentication.isAuthenticated) {
            return jwtService.generateToken(authRequest.username)
        } else {
            throw UsernameNotFoundException("invalid user request !")
        }
    }
}
