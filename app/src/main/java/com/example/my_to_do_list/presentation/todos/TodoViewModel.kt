package com.example.my_to_do_list.presentation.todos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.my_to_do_list.domain.model.DataModel
import com.example.my_to_do_list.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
  private val repository: Repository
) : ViewModel() {

  var state by mutableStateOf(State())
    private set

  init {
    viewModelScope.launch {
      repository.getALlTodos().collect {
        state = state.copy(listOfTodos = it)
      }
    }
  }

  fun createTodo(name: String, time: String,done: Boolean = false){
    val model = DataModel(
      name = name,
      time = time,
      done = done
    )
    upInTodo(model)
  }
  private fun upInTodo(dataModel: DataModel) {
    viewModelScope.launch {
      repository.upInData(dataModel)
    }
  }

  fun deleteTodo(id: Int){
    viewModelScope.launch {
      repository.deleteTodo(id)
    }
  }
}

data class State(
  val listOfTodos: List<DataModel> = emptyList()
)