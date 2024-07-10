package me.ktkoo.toy.domain.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.Date
import java.util.function.Function
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class JwtService(
    @Value("\${jwt.secret}")
    private val SECRET: String,
    @Value("\${jwt.expiry.duration}")
    private val TOKEN_EXPIRY_DURATION_MS: Int
) {

    private val signKey: Key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET))

    // 사용자 이름을 기반으로 JWT 토큰을 생성합니다.
    fun generateToken(userName: String): String {
        val claims: Map<String, Any> = hashMapOf() // JWT에 추가할 클레임이 있다면 추가합니다.
        return createToken(claims, userName) // JWT 토큰을 생성합니다.
    }

    // JWT 토큰을 생성합니다.
    private fun createToken(claims: Map<String, Any>, userName: String): String {
        val now = Date()
        val expiryDate = Date(now.time + TOKEN_EXPIRY_DURATION_MS) // 만료 시간을 설정합니다.
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName) // 토근대상을을 사용자 이름으로 설정
            .setIssuedAt(now) // 토근 발행 시간 설정
            .setExpiration(expiryDate) // 토근 만료 시간 설정
            .signWith(signKey, SignatureAlgorithm.HS256)
            .compact()
    }

    // JWT 토큰에서 사용자 이름을 추출합니다.
    fun extractUsername(token: String): String =
        extractClaim(token, Claims::getSubject)

    // JWT 토큰에서 클레임을 추출합니다.
    private fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T =
        claimsResolver.apply(extractAllClaims(token))

    // 토큰에서 모든 클레임을 추출하는 메서드
    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).body

    // 큰의 유효성 검증: 사용자 이름 일치 및 토큰 만료 여부 확인
    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean =
        extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date =
        extractClaim(token, Claims::getExpiration)
}
