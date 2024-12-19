package ru.quipy.projections.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.quipy.projections.entity.TaskProjection
import java.util.*

interface TaskProjectionRepository : MongoRepository<TaskProjection, UUID>

