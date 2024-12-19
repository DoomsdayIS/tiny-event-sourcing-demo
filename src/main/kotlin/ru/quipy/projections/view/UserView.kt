package ru.quipy.projections.view

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

data class UserView (
    val userId: UUID,
    var nickname: String,
    var username: String,
)

