package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val MY_PROJECT_CREATED_EVENT = "MY_PROJECT_CREATED_EVENT"
const val MY_PROJECT_UPDATE_NAME_EVENT = "MY_PROJECT_UPDATE_NAME_EVENT"
const val MY_PROJECT_ADD_USER_EVENT = "MY_PROJECT_ADD_USER_EVENT"
const val MY_PROJECT_REMOVE_USER_EVENT = "MY_PROJECT_REMOVE_USER_EVENT"
const val MY_PROJECT_ADD_TASK_EVENT = "MY_PROJECT_ADD_TASK_EVENT"
const val MY_PROJECT_REMOVE_TASK_EVENT = "MY_PROJECT_REMOVE_TASK_EVENT"



// API
@DomainEvent(name = MY_PROJECT_CREATED_EVENT)
class MyProjectCreatedEvent(
    val projectId: UUID,
    val projectName: String,
    val createdBy: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<MyProjectAggregate>(
    name = MY_PROJECT_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_PROJECT_UPDATE_NAME_EVENT)
class MyProjectUpdateNameEvent(
    val projectId: UUID,
    val projectName: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<MyProjectAggregate>(
    name = MY_PROJECT_UPDATE_NAME_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_PROJECT_ADD_USER_EVENT)
class MyProjectAddUserEvent(
    val projectId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<MyProjectAggregate>(
    name = MY_PROJECT_ADD_USER_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_PROJECT_REMOVE_USER_EVENT)
class MyProjectRemoveUserEvent(
    val projectId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<MyProjectAggregate>(
    name = MY_PROJECT_REMOVE_USER_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_PROJECT_ADD_TASK_EVENT)
class MyProjectAddTaskEvent(
    val projectId: UUID,
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<MyProjectAggregate>(
    name = MY_PROJECT_ADD_TASK_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = MY_PROJECT_REMOVE_TASK_EVENT)
class MyProjectRemoveTaskEvent(
    val projectId: UUID,
    val taskId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<MyProjectAggregate>(
    name = MY_PROJECT_REMOVE_TASK_EVENT,
    createdAt = createdAt,
)