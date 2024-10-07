package com.example.my_to_do_list.presentation.todos.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoCard(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  onDeleted: () -> Unit = {},
  name: String = "Todo",
  done: Boolean = false
) {
  Card(
    onClick = onClick,
    modifier
      .fillMaxWidth()
      .height(60.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.secondary,
      contentColor = MaterialTheme.colorScheme.primary
    ),
  ) {
    Row(
      modifier
        .fillMaxSize()
        .padding(30.dp, 5.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Start
    ) {
      Text(
        text = name,
        style = TextStyle(
          fontSize = 24.sp,
          fontWeight = FontWeight.SemiBold,
          fontFamily = FontFamily.SansSerif,
        ),
        modifier = Modifier.weight(1f)
      )
      Box(
        modifier
          .fillMaxHeight()
          .aspectRatio(1f)
          .padding(2.dp)
          .clip(RoundedCornerShape(12.dp))
          .clickable(
            onClick = onDeleted
          ),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Delete,
          contentDescription = null,
        )
      }
    }
  }
}