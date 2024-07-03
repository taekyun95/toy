package me.ktkoo.common.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtAuthFilter(private val jwtService: JwtService, private val userDetailsService: UserDetailsService) : OncePerRequestFilter() {

    companion object {
        private const val BEARER_PREFIX = "Bearer "
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val token = extractToken(request)
        token?.let {
            val username = jwtService.extractUsername(it)
            if (SecurityContextHolder.getContext().authentication == null) {
                validateAndAuthenticateUser(username, token, request)
            }
        }
        filterChain.doFilter(request, response)
    }

    // 토큰 추출 메서드
    private fun extractToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(AUTHORIZATION_HEADER)
        return if (authHeader != null && authHeader.startsWith(BEARER_PREFIX)) {
            authHeader.substring(BEARER_PREFIX.length)
        } else null
    }

    private fun validateAndAuthenticateUser(username: String, token: String, request: HttpServletRequest) {
        try {
            val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
            if (jwtService.validateToken(token, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).apply {
                    details = WebAuthenticationDetailsSource().buildDetails(request)
                }
                SecurityContextHolder.getContext().authentication = authToken
                logger.info("Authentication successful for user: $username")
            } else {
                logger.warn("Token validation failed for user: $username")
            }
        } catch (e: UsernameNotFoundException) {
            logger.error("User not found: $username", e)
        } catch (e: Exception) {
            logger.error("An error occurred during user authentication", e)
        }
    }
}
