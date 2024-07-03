package me.ktkoo.toy.domain.auth

import me.ktkoo.toy.infrastructure.user.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetailsImpl {
        val userDetail = userRepository.findByUsername(username)

        return userDetail.map { UserDetailsImpl(it) }
            .orElseThrow { throw UsernameNotFoundException("User not found $username") }
    }
}
