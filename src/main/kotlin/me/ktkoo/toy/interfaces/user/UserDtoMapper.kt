package me.ktkoo.toy.interfaces.user

import me.ktkoo.toy.domain.order.OrderCommand.*
import me.ktkoo.toy.domain.order.OrderInfo
import me.ktkoo.toy.domain.user.UserCommand
import me.ktkoo.toy.interfaces.order.OrderDto
import org.mapstruct.*


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface UserDtoMapper {
    fun of(request: UserDto.RegisterUserRequest): UserCommand.RegisterUser
    fun of(request: String): UserDto.RegisterResponse
}
