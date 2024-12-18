package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.AggregateSubscriptionsManager
import javax.annotation.PostConstruct

@Service
class MyProjectEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(MyProjectEventsSubscriber::class.java)

    @Autowired
    lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(MyProjectAggregate::class, "my-project-aggregate") {

            `when`(MyProjectCreatedEvent::class) { event ->
                logger.info("Project {} created by {}, named {}", event.projectId, event.createdBy, event.projectName)
            }

            `when`(MyProjectUpdateNameEvent::class) { event ->
                logger.info("Project {}. New name {}", event.projectId, event.projectName)
            }

            `when`(MyProjectAddUserEvent::class) { event ->
                logger.info("User {} added to project {}", event.userId, event.projectId)
            }

            `when`(MyProjectRemoveUserEvent::class) { event ->
                logger.info("User {} removed from project {}", event.userId, event.projectId)
            }

            `when`(MyProjectAddTaskEvent::class) { event ->
                logger.info("Task {} added to project {}", event.taskId, event.projectId)
            }

            `when`(MyProjectRemoveTaskEvent::class) { event ->
                logger.info("Task {} removed from project {}", event.taskId, event.projectId)
            }
        }
    }
}