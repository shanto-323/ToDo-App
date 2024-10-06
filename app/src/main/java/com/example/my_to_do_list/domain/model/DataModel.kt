package com.example.my_to_do_list.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.my_to_do_list.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class DataModel(

  @PrimaryKey(autoGenerate = true)
  val id : Int,

  val name : String,
  val time : String,
  val done : Boolean,
)