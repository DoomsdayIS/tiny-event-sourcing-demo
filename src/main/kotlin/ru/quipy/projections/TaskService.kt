package ru.quipy.projections

import org.springframework.stereotype.Service
import ru.quipy.projections.repository.TaskProjectionRepository
import ru.quipy.projections.view.TaskView
import ru.quipy.projections.view.UserView
import java.util.*

@Service
class TaskService (
    private val taskProjectionRepository: TaskProjectionRepository, )
{

    fun findTaskById(id: UUID): TaskView? {
        val task = taskProjectionRepository.findById(id).orElse(null)
        if (task != null) {
            return TaskView(task.taskId, task.projectId, task.name, task.status)
        }
        return null
    }

    fun getTasks(): List<TaskView> {
        val tasks = taskProjectionRepository.findAll()
        val taskViews: MutableList<TaskView> = ArrayList()
        tasks.forEach { task ->
            val taskView = TaskView(task.taskId, task.projectId, task.name, task.status)
            taskViews.add(taskView)
        }
        return taskViews
    }
}