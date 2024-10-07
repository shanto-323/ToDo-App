package com.example.my_to_do_list.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.my_to_do_list.presentation.tasks.Task
import com.example.my_to_do_list.presentation.todos.Todo
import com.example.my_to_do_list.utils.Constants

@Composable
fun Navigation(
  navController: NavHostController = rememberNavController(),
) {
  NavHost(
    navController = navController,
    startDestination = Constants.TODO_SCREEN
  ) {
    composable(Constants.TODO_SCREEN) {
      Todo(
        navHostController = navController
      )
    }
    composable("${Constants.TASKS_SCREEN}/{id}") { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id")?.toInt() ?: -1
      Task(
        navHostController = navController,
        id = id
      )
    }
  }
}