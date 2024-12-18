package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val USER_CREATED_EVENT = "USER_CREATED_EVENT"
const val USER_UPDATE_EVENT = "USER_UPDATE_EVENT"


// API
@DomainEvent(name = USER_CREATED_EVENT)
class UserCreatedEvent(
    val userId: UUID,
    val nickname: String,
    val username: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = USER_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = USER_UPDATE_EVENT)
class UserUpdatedEvent(
    val userId: UUID,
    val nickname: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = USER_UPDATE_EVENT,
    createdAt = createdAt,
)