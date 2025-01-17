package com.example.my_to_do_list.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.my_to_do_list.presentation.navigation.Navigation
import com.example.my_to_do_list.presentation.todos.Todo
import com.example.my_to_do_list.presentation.ui.theme.MyTo_Do_ListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MyTo_Do_ListTheme {
        Navigation()
      }
    }
  }
}
