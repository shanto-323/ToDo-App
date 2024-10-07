package com.example.my_to_do_list.presentation.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_to_do_list.domain.model.DataModel
import com.example.my_to_do_list.domain.model.TasksModel
import com.example.my_to_do_list.domain.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
  private val repository: TasksRepository
) :ViewModel() {
  var state by mutableStateOf(State())
    private set

  fun getTasks(id: Int){
    viewModelScope.launch {
      repository.getTasks(id).collect {
        state = state.copy(listOfTodos = it)
      }
    }
  }

  fun createTasks(id : Int ,name: String,done: Boolean = false){
    val model = TasksModel(
      id = id,
      tasksName = name,
      tasksDone = done
    )
    upInTasks(model)
  }
  private fun upInTasks(tasksModel: TasksModel) {
    viewModelScope.launch {
      repository.upInTasks(tasksModel)
    }
  }

  fun deleteTodo(id: Int){
    viewModelScope.launch {
      repository.deleteTasks(id)
    }
  }
}

data class State(
  val listOfTodos: List<TasksModel> = emptyList()
)