package ru.quipy.logic


import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class UserAggregateState : AggregateState<UUID, UserAggregate> {
    private lateinit var userId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var username: String
    lateinit var nickname: String

    override fun getId() = userId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun userCreatedApply(event: UserCreatedEvent) {
        userId = event.userId
        nickname = event.nickname
        username = event.username
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun userUpdatedApply(event: UserUpdatedEvent) {
        nickname = event.nickname
        updatedAt = event.createdAt
    }
}