package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val MY_TASK_CREATED_EVENT = "MY_TASK_CREATED_EVENT"
const val MY_TASK_UPDATE_NAME_EVENT = "MY_TASK_UPDATE_NAME_EVENT"
const val MY_TASK_ADD_USER_EVENT = "MY_TASK_ADD_USER_EVENT"
const val MY_TASK_REMOVE_USER_EVENT = "MY_TASK_REMOVE_USER_EVENT"
const val MY_TASK_PUT_ON_HOLD_EVENT = "MY_TASK_PUT_ON_HOLD_EVENT"

// API
@DomainEvent(name = MY_TASK_CREATED_EVENT)
class MyTaskCreatedEvent(
    val taskId: UUID,
    val taskName: String,
    val projectId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = MY_TASK_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_TASK_UPDATE_NAME_EVENT)
class MyTaskUpdateNameEvent(
    val taskId: UUID,
    val taskName: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = MY_TASK_UPDATE_NAME_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_TASK_ADD_USER_EVENT)
class MyTaskAddUserEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = MY_TASK_ADD_USER_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_TASK_REMOVE_USER_EVENT)
class MyTaskRemoveUserEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = MY_TASK_REMOVE_USER_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_TASK_PUT_ON_HOLD_EVENT)
class MyTaskPutOnHoldEvent(
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<TaskAggregate>(
    name = MY_TASK_PUT_ON_HOLD_EVENT,
    createdAt = createdAt,
)


