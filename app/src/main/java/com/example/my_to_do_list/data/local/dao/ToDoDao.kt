package com.example.my_to_do_list.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.my_to_do_list.domain.model.DataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

  @Upsert
  suspend fun upInUser(dataModel: DataModel)

  @Query("SELECT * FROM todo_table ORDER BY id DESC")
  fun getAllToDo(): Flow<List<DataModel>>

  @Query("DELETE FROM todo_table WHERE id = :id")
  suspend fun deleteToDo(id: Int)
}