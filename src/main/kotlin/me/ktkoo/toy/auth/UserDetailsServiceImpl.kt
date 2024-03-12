package me.ktkoo.toy.auth

import me.ktkoo.toy.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val userDetail = userRepository.findByUsername(username)

        return userDetail.map { UserDetail(it) }
            .orElseThrow { UsernameNotFoundException("User not found $username") }
    }
}
