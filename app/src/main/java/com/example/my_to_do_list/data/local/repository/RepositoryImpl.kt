package com.example.my_to_do_list.data.local.repository

import com.example.my_to_do_list.data.local.dao.ToDoDao
import com.example.my_to_do_list.domain.model.DataModel
import com.example.my_to_do_list.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
   private  val todoDao: ToDoDao
) : Repository{
  override suspend fun upInData(dataModel: DataModel) = todoDao.upInUser(dataModel)

  override suspend fun getALlTodos(): Flow<List<DataModel>> = todoDao.getAllToDo()

  override suspend fun deleteTodo(id: Int) = todoDao.deleteToDo(id)

  override suspend fun getSingleTodo(id: Int) = todoDao.getSingleTodo(id)
}