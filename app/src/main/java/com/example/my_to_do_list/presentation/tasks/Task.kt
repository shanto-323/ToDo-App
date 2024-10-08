package com.example.my_to_do_list.presentation.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.my_to_do_list.presentation.tasks.items.MyShapeLine
import com.example.my_to_do_list.presentation.tasks.items.TaskCard
import com.example.my_to_do_list.presentation.todos.items.TodoCard
import com.example.my_to_do_list.utils.Constants
import java.time.LocalTime

@Composable
fun Task(
  modifier: Modifier = Modifier,
  viewModel: TaskViewModel = hiltViewModel(),
  navigateBack: () -> Unit,
  id: Int = 0
) {

  LaunchedEffect(viewModel) {
    viewModel.getTasks(id)
  }

  var name by rememberSaveable {
    mutableStateOf("")
  }
  var showDialog by rememberSaveable { mutableStateOf(false) }

  Scaffold(
    topBar = {
      Row(
        modifier
          .fillMaxWidth()
          .fillMaxHeight(0.12f)
          .statusBarsPadding()
          .background(MaterialTheme.colorScheme.primary)
          .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = {navigateBack()}
        ) {
          Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Add Todo",
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(32.dp)
          )
        }

        Text(
          text = viewModel.state.singleDataModel.name,
          style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.secondary
          ),
          modifier = Modifier
            .weight(1f)
            .padding(start = 20.dp),
          textAlign = TextAlign.Start
        )
      }
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = { showDialog = true },
        modifier
          .padding(20.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        elevation = FloatingActionButtonDefaults.elevation(
          defaultElevation = 2.dp,
          pressedElevation = 10.dp,
          hoveredElevation = 10.dp
        )
      ) {
        Icon(
          imageVector = Icons.Default.Add,
          contentDescription = "Add Todo",
          tint = MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(32.dp)
        )
      }
    },
    content = {
      Column(
        modifier
          .fillMaxSize()
          .statusBarsPadding()
          .systemBarsPadding()
          .background(MaterialTheme.colorScheme.tertiary)
          .padding(it)
          .padding(10.dp, 2.dp)
      ) {
        LazyColumn {
          items(viewModel.state.listOfTasks) { todoItems ->
            Box(
              modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .padding(2.dp)
            ) {
              TaskCard(
                name = todoItems.tasksName,
                isDoneClicked = {
                  viewModel.updateTasks(
                    taskId = todoItems.tasksId,
                    id = todoItems.id,
                    name = todoItems.tasksName,
                    done = !todoItems.tasksDone
                  )
                },
                taskDone = todoItems.tasksDone,
                onDeleted = {
                  viewModel.deleteTodo(todoItems.tasksId)
                }
              )
            }
          }
          item {
            Box(
              modifier
                .height(80.dp)
                .fillMaxWidth()
                .background(Color.Transparent)
            ) {}
          }
        }
      }

      if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
          Column(
            modifier
              .fillMaxHeight(0.3f)
              .fillMaxWidth()
              .clip(RoundedCornerShape(20.dp))
              .border(5.dp, MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(20.dp))
              .background(MaterialTheme.colorScheme.primary),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            OutlinedTextField(
              value = name,
              onValueChange = { name = it },
              textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
              ),
              singleLine = true,
              label = {
                Text(text = "Task Name")
              },
              colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                cursorColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.secondary,
                focusedLabelColor = MaterialTheme.colorScheme.secondary,
                unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
                selectionColors = TextSelectionColors(
                  handleColor = MaterialTheme.colorScheme.secondary,
                  backgroundColor = Color.LightGray
                )
              )
            )
            Button(
              onClick = {
                viewModel.createTasks(id = id, name = name)
                showDialog = false
                name = ""
              },
              modifier.fillMaxWidth(0.8f),
              colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.primary
              )
            ) {
              Text(text = "Create Task")
            }
          }
        }
      }
    }
  )
}