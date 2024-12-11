package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

fun MyProjectAggregateState.create(id: UUID, name: String, authorId: UUID): MyProjectCreatedEvent {
    return MyProjectCreatedEvent(
        projectId = id,
        projectName = name,
        createdBy = authorId,
    )
}

fun MyProjectAggregateState.rename(name: String): MyProjectUpdateNameEvent {
    return MyProjectUpdateNameEvent(
        projectId = this.getId(),
        projectName = name,
    )
}

fun MyProjectAggregateState.addUser(userId: UUID): MyProjectAddUserEvent {
    if (userIds.contains(userId)) {
        throw IllegalArgumentException("User is already in project")
    }
    return MyProjectAddUserEvent(
        projectId = this.getId(),
        userId = userId,
    )
}

fun MyProjectAggregateState.removeUser(userId: UUID): MyProjectRemoveUserEvent {
    if (userIds.size == 1 || !userIds.contains(userId)) {
        throw IllegalArgumentException("There is no such user in the project or it is the last user")
    }
    return MyProjectRemoveUserEvent(
        projectId = this.getId(),
        userId = userId,
    )
}

fun MyProjectAggregateState.addTask(taskId: UUID): MyProjectAddTaskEvent {
    if (taskIds.contains(taskId)) {
        throw IllegalArgumentException("Task is already in project")
    }
    return MyProjectAddTaskEvent(
        projectId = this.getId(),
        taskId = taskId,
    )
}

fun MyProjectAggregateState.removeTask(taskId: UUID): MyProjectRemoveTaskEvent {
    if (!taskIds.contains(taskId)) {
        throw IllegalArgumentException("There is no such task")
    }
    return MyProjectRemoveTaskEvent(
        projectId = this.getId(),
        taskId = taskId,
    )
}