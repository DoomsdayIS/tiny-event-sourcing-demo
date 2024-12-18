package ru.quipy.logic


import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class MyProjectAggregateState : AggregateState<UUID, MyProjectAggregate> {
    private lateinit var projectId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var name: String
    var userIds: ArrayList<UUID> = arrayListOf()
    var taskIds: ArrayList<UUID> = arrayListOf()

    override fun getId() = projectId

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun myProjectCreatedApply(event: MyProjectCreatedEvent) {
        projectId = event.projectId
        name = event.projectName
        userIds.add(event.createdBy)
        createdAt = event.createdAt
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myProjectUpdateNameApply(event: MyProjectUpdateNameEvent) {
        name = event.projectName
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myProjectAddUserApply(event: MyProjectAddUserEvent) {
        userIds.add(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myProjectRemoveUserApply(event: MyProjectRemoveUserEvent) {
        userIds.remove(event.userId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myProjectAddTaskApply(event: MyProjectAddTaskEvent) {
        taskIds.add(event.taskId)
        updatedAt = event.createdAt
    }

    @StateTransitionFunc
    fun myProjectRemoveTaskApply(event: MyProjectRemoveTaskEvent) {
        taskIds.remove(event.taskId)
        updatedAt = event.createdAt
    }

}