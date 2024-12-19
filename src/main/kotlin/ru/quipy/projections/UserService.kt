package ru.quipy.projections

import org.springframework.stereotype.Service
import ru.quipy.projections.repository.UserProjectionRepository
import ru.quipy.projections.view.UserView
import java.util.*

@Service
class UserService (
    private val userProjectionRepository: UserProjectionRepository, )
{

    fun findUserById(id: UUID): UserView? {
        val user = userProjectionRepository.findById(id).orElse(null)
        if (user != null) {
            return UserView(user.userId, user.nickname, user.username)
        }
        return null
    }

    fun getUsers(): List<UserView> {
        val users = userProjectionRepository.findAll()
        val usersViews: MutableList<UserView> = ArrayList()
        users.forEach { user ->
            val userView = UserView(user.userId, user.nickname, user.username)
            usersViews.add(userView)
        }
        return usersViews
    }
}