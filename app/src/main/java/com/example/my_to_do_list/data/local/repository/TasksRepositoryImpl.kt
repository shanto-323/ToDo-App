package com.example.my_to_do_list.data.local.repository

import com.example.my_to_do_list.data.local.dao.ToDoTasksDao
import com.example.my_to_do_list.domain.model.TasksModel
import com.example.my_to_do_list.domain.repository.TasksRepository
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl(
  private val dao: ToDoTasksDao
) : TasksRepository {
  override fun getTasks(id: Int): Flow<List<TasksModel>> = dao.getAllTodosById(id)
}