package com.example.my_to_do_list.domain.repository

import com.example.my_to_do_list.domain.model.DataModel
import kotlinx.coroutines.flow.Flow

interface Repository {

  suspend fun upInData(dataModel: DataModel)

  suspend fun getALlTodos(): Flow<List<DataModel>>

  suspend fun deleteTodo(id:Int)

  suspend fun getSingleTodo(id: Int) : DataModel
}