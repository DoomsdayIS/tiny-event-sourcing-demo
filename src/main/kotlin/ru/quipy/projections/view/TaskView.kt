package ru.quipy.projections.view

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import ru.quipy.logic.TaskStatus
import java.util.*

data class TaskView (
    val taskId: UUID,
    val projectID: UUID,
    val name: String,
    val status: TaskStatus,
)

