package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/my-projects")
class MyProjectController(
    val myProjectEsService: EventSourcingService<UUID, MyProjectAggregate, MyProjectAggregateState>
) {
    @PostMapping("")
    fun createProject(@RequestParam name: String, @RequestParam authorId: UUID) : MyProjectCreatedEvent {
        return myProjectEsService.create { it.create(UUID.randomUUID(), name, authorId) }
    }

    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: UUID) : MyProjectAggregateState? {
        return myProjectEsService.getState(projectId)
    }

    @PutMapping("/{projectId}/rename/{name}")
    fun renameProject(@PathVariable projectId: UUID, @PathVariable name: String) : MyProjectUpdateNameEvent {
        return myProjectEsService.update(projectId) {
            it.rename(name)
        }
    }

    @PutMapping("/{projectId}/addUser/{userId}")
    fun addUserToProject(@PathVariable projectId: UUID, @PathVariable userId: UUID) : MyProjectAddUserEvent {
        return myProjectEsService.update(projectId) {
            it.addUser(userId)
        }
    }

    @PutMapping("/{projectId}/removeUser/{userId}")
    fun removeUserFromProject(@PathVariable projectId: UUID, @PathVariable userId: UUID) : MyProjectRemoveUserEvent {
        return myProjectEsService.update(projectId) {
            it.removeUser(userId)
        }
    }

    @PutMapping("/{projectId}/addTask/{taskId}")
    fun addTaskToProject(@PathVariable projectId: UUID, @PathVariable taskId: UUID) : MyProjectAddTaskEvent {
        return myProjectEsService.update(projectId) {
            it.addTask(taskId)
        }
    }

    @PutMapping("/{projectId}/removeTask/{taskId}")
    fun removeTaskFromProject(@PathVariable projectId: UUID, @PathVariable taskId: UUID) : MyProjectRemoveTaskEvent {
        return myProjectEsService.update(projectId) {
            it.removeTask(taskId)
        }
    }

}