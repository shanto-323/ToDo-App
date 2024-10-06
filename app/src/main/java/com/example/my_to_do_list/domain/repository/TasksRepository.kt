package com.example.my_to_do_list.domain.repository

import com.example.my_to_do_list.domain.model.TasksModel
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
  fun getTasks(id: Int): Flow<List<TasksModel>>
}