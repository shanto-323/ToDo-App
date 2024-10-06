package com.example.my_to_do_list.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.my_to_do_list.domain.model.TasksModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoTasksDao {

  @Query("SELECT * FROM tasks_table WHERE id = :id ORDER BY tasksId ASC")
  fun getAllTodosById(id:Int) : Flow<List<TasksModel>>

  @Upsert
  suspend fun upInTasks(tasksModel: TasksModel)

  @Query("DELETE FROM tasks_table WHERE tasksId = :id")
  suspend fun deleteTasks(id: Int)

  @Query("DELETE FROM tasks_table WHERE id = :id")
  suspend fun deleteTasksById(id: Int)
}