package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var name: String
    lateinit var projectId: UUID
    lateinit var status: TaskStatus
    var userIds: ArrayList<UUID> = arrayListOf()

    override fun getId() = taskId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun myTaskCreatedApply(event: MyTaskCreatedEvent) {
        taskId = event.taskId
        name = event.taskName
        projectId = event.projectId
        status = TaskStatus.CREATED
        createdAt = event.createdAt
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myTaskUpdateNameApply(event: MyTaskUpdateNameEvent) {
        name = event.taskName
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myTaskAddUserApply(event: MyTaskAddUserEvent) {
        userIds.add(event.userId)
        updatedAt = event.createdAt
        status = TaskStatus.ASSIGNED
    }

    @StateTransitionFunc
    fun myTaskRemoveUserApply(event: MyTaskRemoveUserEvent) {
        userIds.remove(event.userId)
        if (userIds.size == 0) {
            status = TaskStatus.UNASSIGNED
        }
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myTaskPutOnHoldApply(event: MyTaskPutOnHoldEvent) {
        status = TaskStatus.HOLD
        updatedAt = event.createdAt
    }
}

enum class TaskStatus {
    CREATED, ASSIGNED, UNASSIGNED, HOLD
}