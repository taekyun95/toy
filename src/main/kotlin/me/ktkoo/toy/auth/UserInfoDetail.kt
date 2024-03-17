package me.ktkoo.toy.auth

import me.ktkoo.toy.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(user: User) : UserDetails {

    private val username: String = user.username
    private val password: String = user.password
    private val authorities: Collection<GrantedAuthority> = user.roles.split(",")
        .map { role -> SimpleGrantedAuthority(role.trim()) }

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
