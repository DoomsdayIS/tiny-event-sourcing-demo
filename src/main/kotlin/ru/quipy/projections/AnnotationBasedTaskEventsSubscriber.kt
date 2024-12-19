package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(
    aggregateClass = TaskAggregate::class, subscriberName = "tasks-stream"
)
class AnnotationBasedTaskEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(AnnotationBasedTaskEventsSubscriber::class.java)

    @SubscribeEvent
    fun taskCreatedSubscriber(event: MyTaskCreatedEvent) {
        logger.info("Task created: {}", event.taskName)
    }

    @SubscribeEvent
    fun taskUpdateSubscriber(event: MyTaskUpdateNameEvent) {
        logger.info("Task {} name updated: {}", event.taskId, event.taskName)
    }

    @SubscribeEvent
    fun taskAddUserSubscriber(event: MyTaskAddUserEvent) {
        logger.info("User {} added into the to task {}: ", event.userId, event.taskId)
    }

    @SubscribeEvent
    fun taskRemoveUserSubscriber(event: MyTaskRemoveUserEvent) {
        logger.info("User {} removed from the task {}: ", event.userId, event.taskId)
    }

    @SubscribeEvent
    fun taskPutOnHoldSubscriber(event: MyTaskPutOnHoldEvent) {
        logger.info("Task {} held", event.taskId)
    }
}