package com.example.my_to_do_list.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.my_to_do_list.data.local.dao.ToDoDao
import com.example.my_to_do_list.domain.model.DataModel

@Database(
  entities = [DataModel::class],
  version = 1,
  exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {
  abstract fun dao() : ToDoDao
}