package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct

@Service
class TaskEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(TaskEventsSubscriber::class.java)

    @Autowired
    lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TaskAggregate::class, "task-aggregate") {

            `when`(MyTaskCreatedEvent::class) { event ->
                logger.info("Task created: {}", event.taskName)
            }

            `when`(MyTaskUpdateNameEvent::class) { event ->
                logger.info("Task {} name updated: {}", event.taskId, event.taskName)
            }

            `when`(MyTaskAddUserEvent::class) { event ->
                logger.info("User {} added into the to task {}: ", event.userId, event.taskId)
            }
            `when`(MyTaskRemoveUserEvent::class) { event ->
                logger.info("User {} removed from the task {}: ", event.userId, event.taskId)
            }
            `when`(MyTaskPutOnHoldEvent::class) { event ->
                logger.info("Task {} held", event.taskId)
            }
        }
    }
}