package ru.quipy.controller

import liquibase.pro.packaged.it
import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController (
    val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {
    @PostMapping("")
    fun createTask(@RequestParam name: String, @RequestParam projectId: UUID) : MyTaskCreatedEvent {
        return taskEsService.create { it.create(UUID.randomUUID(), name, projectId) }
    }

    @GetMapping("/{taskId}")
    fun getTask(@PathVariable taskId: UUID) : TaskAggregateState? {
        return taskEsService.getState(taskId)
    }

    @PutMapping("/{taskId}/rename/{name}")
    fun renameTask(@PathVariable taskId: UUID, @PathVariable name: String) : MyTaskUpdateNameEvent {
        return taskEsService.update(taskId) { it.rename(name) }
    }

    @PutMapping("/{taskId}/addUser/{userId}")
    fun addUserToTask(@PathVariable taskId: UUID, @PathVariable userId: UUID) : MyTaskAddUserEvent {
        return taskEsService.update(taskId) { it.addUser(userId) }
    }

    @PutMapping("/{taskId}/removeUser/{userId}")
    fun removeUserFromTask(@PathVariable taskId: UUID, @PathVariable userId: UUID) : MyTaskRemoveUserEvent {
        return taskEsService.update(taskId) { it.removeUser(userId) }
    }

    @PutMapping("/{taskId}/putOnHold")
    fun putTaskOnHold(@PathVariable taskId: UUID) : MyTaskPutOnHoldEvent {
        return taskEsService.update(taskId) { it.putOnHold() }
    }

}