package com.example.my_to_do_list.presentation.tasks.items

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaskCard(
  modifier: Modifier = Modifier,
  name: String = "Todo",
  taskDone: Boolean = true,
  isDoneClicked: () -> Unit = {},
  onDeleted: () -> Unit = {},
) {
  Card(
    modifier
      .fillMaxWidth()
      .height(45.dp),
    colors = CardDefaults.cardColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f),
    ),
    shape = RoundedCornerShape(6.dp)
  ) {
    Row(
      modifier
        .fillMaxSize()
        .padding(10.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        modifier
          .fillMaxHeight()
          .weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
      ){
        Box(
          modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(
              onClick = isDoneClicked
            )
            .border(2.dp, Color.Black, RoundedCornerShape(4.dp)),
          contentAlignment = Alignment.Center
        ) {
          if (taskDone) {
            Icon(
              imageVector = Icons.Default.Done,
              contentDescription = null,
              tint = Color.Black,
              modifier = Modifier.padding(3.dp)
            )
          }
        }
        Box(
          modifier
            .weight(1f)
            .padding(10.dp, 0.dp,40.dp,0.dp),
          contentAlignment = Alignment.Center
        ) {
          Text(
            modifier = Modifier.fillMaxWidth()
              .padding(5.dp,0.dp),
            text = name,
            style = TextStyle(
              fontSize = 24.sp,
              fontWeight = FontWeight.SemiBold,
              color = if (taskDone) Color.DarkGray else Color.Black,
              textAlign = TextAlign.Left
            )
          )
          if (taskDone) {
            MyShapeLine()
          }
        }
      }
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
          tint = Color.Black
        )
      }
    }
  }
}


@Composable
fun MyShapeLine(
  modifier: Modifier = Modifier,
  color: Color = Color.Black
) {
  Canvas(
    modifier
      .fillMaxWidth()
  ) {
    drawLine(
      start = Offset(0f, size.height / 2),
      end = Offset(size.width, size.height / 2),
      strokeWidth = 5f,
      color = color
    )
  }
}

