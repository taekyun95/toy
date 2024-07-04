package me.ktkoo.config

import me.ktkoo.common.interceptor.CommonHttpRequestInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(CommonHttpRequestInterceptor()).addPathPatterns("/api/**")
    }
}
