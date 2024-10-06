package com.example.my_to_do_list.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my_to_do_list.data.local.dao.ToDoDao
import com.example.my_to_do_list.data.local.dao.ToDoTasksDao
import com.example.my_to_do_list.domain.model.DataModel
import com.example.my_to_do_list.domain.model.TasksModel

@Database(
  entities = [DataModel::class, TasksModel::class],
  version = 2,
  exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
  abstract fun dao() : ToDoDao
  abstract fun tasksDao() : ToDoTasksDao
}