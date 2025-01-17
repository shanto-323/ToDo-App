package com.example.my_to_do_list.presentation.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_to_do_list.domain.model.DataModel
import com.example.my_to_do_list.domain.model.TasksModel
import com.example.my_to_do_list.domain.repository.Repository
import com.example.my_to_do_list.domain.repository.TasksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
  private val repository: TasksRepository,
  private val singleRepo: Repository
) : ViewModel() {
  var state by mutableStateOf(State())
    private set

  fun getTasks(id: Int) {
    viewModelScope.launch {
      val listFlow = async {
        repository.getTasks(id).collect {
          state = state.copy(listOfTasks = it)
        }
      }
      val singleFlow = async {
        val data = singleRepo.getSingleTodo(id)
        state = state.copy(
          singleDataModel = data
        )
      }
      listFlow.await()
      singleFlow.await()
    }
  }


  fun createTasks(id: Int, name: String, done: Boolean = false) {
    val model = TasksModel(
      id = id,
      tasksName = name,
      tasksDone = done
    )
    upInTasks(model)
  }

  fun updateTasks(taskId: Int, id: Int, name: String, done: Boolean = false) {
    val model = TasksModel(
      tasksId = taskId,
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

  fun deleteTodo(id: Int) {
    viewModelScope.launch {
      repository.deleteTasks(id)
    }
  }
}

data class State(
  val listOfTasks: List<TasksModel> = emptyList(),
  val singleDataModel: DataModel = DataModel()
)