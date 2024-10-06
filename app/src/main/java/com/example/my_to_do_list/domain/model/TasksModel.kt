package com.example.my_to_do_list.domain.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.my_to_do_list.utils.Constants

@Entity(
  tableName = Constants.TASKS_TABLE_NAME,
  foreignKeys = [
    ForeignKey(
      entity = DataModel::class,
      parentColumns = ["id"],
      childColumns = ["id"],
      onDelete = ForeignKey.CASCADE,
      onUpdate = ForeignKey.CASCADE
    )
  ]
)
data class TasksModel (
  @PrimaryKey(autoGenerate = true)
  val tasksId : Int = 0,

  val id : Int,   //AKA ForeignKey
  val tasksName : String,
  val tasksDone : Boolean,
)
