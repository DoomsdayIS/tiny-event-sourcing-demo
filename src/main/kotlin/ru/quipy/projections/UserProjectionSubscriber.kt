package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.projections.entity.UserProjection
import ru.quipy.projections.repository.UserProjectionRepository
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(aggregateClass = UserAggregate::class, subscriberName = "user-subscriber")
class UserProjectionSubscriber(private val userProjectionRepository: UserProjectionRepository) {

    val logger: Logger = LoggerFactory.getLogger(UserProjectionSubscriber::class.java)

    @SubscribeEvent
    fun onUserCreated(event: UserCreatedEvent) {
        logger.info("Handling UserCreatedEvent for userId: {}", event.userId)
        val userProjection = UserProjection(
            userId = event.userId,
            username = event.username,
            nickname = event.nickname,
        )
        userProjectionRepository.save(userProjection)

    }

    @SubscribeEvent
    fun onUserUpdated(event: UserUpdatedEvent) {
        logger.info("Handling UserUpdatedEvent for userId: {}", event.userId)
        val user = userProjectionRepository.findById(event.userId).orElse(null)
        if (user != null) {
            user.nickname = event.nickname
            userProjectionRepository.save(user)
        }
    }
}