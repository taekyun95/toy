package me.ktkoo.config

import java.time.Duration
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.CacheErrorHandler
import org.springframework.cache.interceptor.SimpleCacheErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
@EnableCaching // spring의 캐싱 기능 활성화
class RedisCacheConfig(
    @Value("\${spring.cache.redis.host}")
    private val redisHost: String,

    @Value("\${spring.cache.redis.port}")
    private val redisPort: Int
) {
    companion object {
        private val logger = LoggerFactory.getLogger(RedisCacheConfig::class.java)
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        logger.info("Configuring Redis connection. Host: {}, Port: {}", redisHost, redisPort)
        val factory = LettuceConnectionFactory(redisHost, redisPort)  // Lettuce는 비동기 처리가 가능한 Redis 클라이언트 라이브러리
        factory.afterPropertiesSet()  // 팩토리 초기화 작업
        testRedisConnection(factory)  // Redis 연결을 테스트하는 함수 호출
        return factory
    }

    private fun testRedisConnection(factory: RedisConnectionFactory) {
        val template = RedisTemplate<String, String>()
        template.setConnectionFactory(factory)
        template.keySerializer = StringRedisSerializer()  // Key 직렬화 방식 지정
        template.valueSerializer = StringRedisSerializer()  // Value 직렬화 방식 지정
        template.afterPropertiesSet()
        try {
            val response = template.execute { connection -> connection.ping() }  // Redis 서버에 ping을 보내 연결 상태 확인
            logger.info("Redis connection PING response: {}", response)
        } catch (e: Exception) {
            logger.error("Failed to connect to Redis", e)
        }
    }
    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        logger.info("Setting up RedisTemplate.")
        val template = RedisTemplate<String, Any>()
        template.setConnectionFactory(redisConnectionFactory())  // Redis 연결 팩토리 설정
        template.keySerializer = StringRedisSerializer()  // Key 직렬화 방식 지정
        template.valueSerializer = GenericJackson2JsonRedisSerializer()  // Value를 JSON 형식으로 직렬화
        return template
    }

    @Bean
    fun cacheManager(): CacheManager {
        val config = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))  // Key 직렬화 방식 설정
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))  // Value 직렬화 방식 설정
            .entryTtl(Duration.ofHours(1))  // 캐시 데이터의 TTL(유효 기간)을 1시간으로 설정

        return RedisCacheManager.builder(redisConnectionFactory())
            .cacheDefaults(config)  // 위에서 설정한 캐시 구성을 기본값으로 사용
            .transactionAware()  // 트랜잭션 시 캐시 변경 사항을 트랜잭션 관리
            .build()
    }

    @Bean
    fun cacheErrorHandler(): CacheErrorHandler {
        return SimpleCacheErrorHandler()  // 캐시 작업 중 발생하는 예외를 처리하는 기본 에러 핸들러
    }
}
