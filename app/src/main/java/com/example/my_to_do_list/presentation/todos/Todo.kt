package com.example.my_to_do_list.presentation.todos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import java.time.LocalTime

@Composable
fun Todo(
  modifier: Modifier = Modifier,
  viewModel: TodoViewModel = hiltViewModel()
) {
  var name by rememberSaveable {
    mutableStateOf("")
  }
  var date by rememberSaveable {
    mutableStateOf("")
  }
  var showDialog by rememberSaveable { mutableStateOf(false) }

  Column(
    modifier
      .fillMaxSize()
      .statusBarsPadding()
      .systemBarsPadding()
      .background(Color.White),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    LazyColumn {
      items(viewModel.state.listOfTodos) { todoItems ->
        Box(
          modifier
            .fillMaxWidth()
            .fillMaxHeight(0.2f)
        ) {
          Text(text = todoItems.name)
        }
      }
    }

    FloatingActionButton(
      onClick = { showDialog = true },
      modifier
        .align(Alignment.End)
        .padding(40.dp)
    ) {
      Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo", tint = Color.White)
    }

    if (showDialog){
      Dialog(onDismissRequest = {showDialog = false}) {
        Column(
          modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth()
            .background(Color.White),
          verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            textStyle = TextStyle(
              color = Color.Black,
              fontSize = 18.sp,
              fontWeight = FontWeight.SemiBold
            ),
            singleLine = true,
            label = {
              Text(text = "Enter Todo Name")
            }
          )
          Button(
            onClick = {
              viewModel.createTodo(name, LocalTime.now().toString())
              showDialog = false
            },
            modifier.fillMaxWidth(0.8f)
          ) {
            Text(text = "Create Todo")
          }
        }
      }
    }
  }
}