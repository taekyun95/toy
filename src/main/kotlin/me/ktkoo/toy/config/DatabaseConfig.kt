package me.ktkoo.toy.config

import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class DatabaseConfig(
    @Value("\${spring.datasource.url}")
    private val dbUrl: String,

    @Value("\${spring.datasource.username}")
    private val dbUsername: String,

    @Value("\${spring.datasource.password}")
    private val dbPassword: String
) {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .url(dbUrl)
            .username(dbUsername)
            .password(dbPassword)
            .type(HikariDataSource::class.java)
            .build()
    }
}
