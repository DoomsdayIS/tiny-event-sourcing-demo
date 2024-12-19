package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.logic.TaskStatus
import ru.quipy.projections.entity.TaskProjection
import ru.quipy.projections.repository.TaskProjectionRepository
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent
import java.util.*

@Service
@AggregateSubscriber(aggregateClass = TaskAggregate::class, subscriberName = "task-subscriber")
class TaskProjectionSubscriber(private val taskProjectionRepository: TaskProjectionRepository) {

    val logger: Logger = LoggerFactory.getLogger(TaskProjectionSubscriber::class.java)

    @SubscribeEvent
    fun onMyTaskCreatedEvent(event: MyTaskCreatedEvent) {
        logger.info("Handling MyTaskCreatedEvent for taskId: {}", event.taskId)
        val taskProjection = TaskProjection(
            taskId = event.taskId,
            name=event.taskName,
            projectId = event.projectId,
            status = TaskStatus.ASSIGNED,
            userIds = ArrayList<UUID>()
        )
        taskProjectionRepository.save(taskProjection)
    }

    @SubscribeEvent
    fun onMyTaskUpdateNameEvent(event: MyTaskUpdateNameEvent) {
        logger.info("Handling MyTaskUpdateNameEvent for taskId: {}", event.taskId)
        val task = taskProjectionRepository.findById(event.taskId).orElse(null)
        if (task != null) {
            task.name = event.taskName
            taskProjectionRepository.save(task)
        }
    }

    @SubscribeEvent
    fun onMyTaskAddUserEvent(event: MyTaskAddUserEvent) {
        logger.info("Handling MyTaskAddUserEvent for taskId: {}", event.taskId)
        val task = taskProjectionRepository.findById(event.taskId).orElse(null)
        if (task != null) {
            task.userIds.add(event.userId)
            taskProjectionRepository.save(task)
        }
    }

    @SubscribeEvent
    fun onMyTaskRemoveUserEvent(event: MyTaskRemoveUserEvent) {
        logger.info("Handling MyTaskRemoveUserEvent for taskId: {}", event.taskId)
        val task = taskProjectionRepository.findById(event.taskId).orElse(null)
        if (task != null) {
            task.userIds.remove(event.userId)
            taskProjectionRepository.save(task)
        }
    }

    @SubscribeEvent
    fun onMyTaskPutOnHoldEvent(event: MyTaskPutOnHoldEvent) {
        logger.info("Handling MyTaskPutOnHoldEvent for taskId: {}", event.taskId)
        val task = taskProjectionRepository.findById(event.taskId).orElse(null)
        if (task != null) {
            task.status = TaskStatus.HOLD
            taskProjectionRepository.save(task)
        }
    }
}