package ru.quipy.projections.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.quipy.logic.TaskStatus
import java.util.*

@Document(collection = "tasks")
data class TaskProjection (
    @Id
    var taskId: UUID,
    var name: String,
    var status: TaskStatus,
    var projectId: UUID,
    var userIds: ArrayList<UUID> = ArrayList<UUID>()
)

