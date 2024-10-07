package com.example.my_to_do_list.domain.repository

import com.example.my_to_do_list.domain.model.TasksModel
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

  suspend fun getTasks(id: Int): Flow<List<TasksModel>>

  suspend fun upInTasks(tasksModel: TasksModel)

  suspend fun deleteTasks(id: Int)
}