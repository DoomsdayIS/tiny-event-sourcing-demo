package ru.quipy.logic

import ru.quipy.api.*
import java.util.*


fun UserAggregateState.create(id: UUID, name: String, nickname: String): UserCreatedEvent {
    return UserCreatedEvent(
        userId = id,
        nickname = nickname,
        username = name,
    )
}

fun UserAggregateState.updateNickname(nickname: String): UserUpdatedEvent {
    return UserUpdatedEvent(
        userId = this.getId(),
        nickname = nickname,
    )
}
