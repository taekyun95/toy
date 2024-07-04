package me.ktkoo.toy.domain.user

import org.mapstruct.*

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.ERROR)
interface UserInfoMapper {
    @Mappings(Mapping(source = "user.id", target = "userId"))
    fun of(user: User): UserInfo.Main
}
