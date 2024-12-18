package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

fun TaskAggregateState.create(id: UUID, name: String, projectId: UUID): MyTaskCreatedEvent {
    return MyTaskCreatedEvent(
        taskId = id,
        taskName = name,
        projectId = projectId,
    )
}

fun TaskAggregateState.rename(name: String): MyTaskUpdateNameEvent {
    return MyTaskUpdateNameEvent(
        taskId = this.getId(),
        taskName = name,
    )
}

fun TaskAggregateState.addUser(userId: UUID): MyTaskAddUserEvent {
    if (userIds.contains(userId)) {
        throw IllegalArgumentException("User is already in the task")
    }
    return MyTaskAddUserEvent(
        taskId = this.getId(),
        userId = userId,
    )
}

fun TaskAggregateState.removeUser(userId: UUID): MyTaskRemoveUserEvent {
    if (!userIds.contains(userId)) {
        throw IllegalArgumentException("There is no user with the id $userId in the task")
    }
    return MyTaskRemoveUserEvent(
        taskId = this.getId(),
        userId = userId,
    )
}

fun TaskAggregateState.putOnHold(): MyTaskPutOnHoldEvent {
    return MyTaskPutOnHoldEvent(
        taskId = this.getId(),
    )
}