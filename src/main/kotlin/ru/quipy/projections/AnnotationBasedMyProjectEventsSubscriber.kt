package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(
    aggregateClass = MyProjectAggregate::class, subscriberName = "my-project-subscriber",
)
class AnnotationBasedMyProjectEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(AnnotationBasedProjectEventsSubscriber::class.java)

    @SubscribeEvent
    fun projectCreatedSubscriber(event: MyProjectCreatedEvent) {
        logger.info("Project {} created by {}, named {}", event.projectId, event.createdBy, event.projectName)
    }

    @SubscribeEvent
    fun projectUpdatedSubscriber(event: MyProjectUpdateNameEvent) {
        logger.info("Project {}. New name {}", event.projectId, event.projectName)
    }

    @SubscribeEvent
    fun projectAddUserSubscriber(event: MyProjectAddUserEvent) {
        logger.info("User {} added to project {}", event.userId, event.projectId)
    }

    @SubscribeEvent
    fun projectRemoveUserSubscriber(event: MyProjectRemoveUserEvent) {
        logger.info("User {} removed from project {}", event.userId, event.projectId)
    }

    @SubscribeEvent
    fun projectAddTaskSubscriber(event: MyProjectAddTaskEvent) {
        logger.info("Task {} added to project {}", event.taskId, event.projectId)
    }

    @SubscribeEvent
    fun projectRemoveTaskSubscriber(event: MyProjectRemoveTaskEvent) {
        logger.info("Task {} removed from project {}", event.taskId, event.projectId)
    }
}