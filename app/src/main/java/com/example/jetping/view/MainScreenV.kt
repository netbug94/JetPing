package com.example.jetping.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun MainScreenV() {
    var fieldValue by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
// Container
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
// Title
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)) {
                Text(text = "JetPing", Modifier.scale(1.5f))
            }
// Input row
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().weight(1f).padding(vertical = 12.dp)) {
                Button(modifier = Modifier.fillMaxSize().weight(2f), shape = RectangleShape,
                    onClick = { fieldValue = "" }) {
                    Text(text = "Clear")
                }
                TextField(modifier = Modifier.fillMaxSize().weight(5f), value = fieldValue, onValueChange = { newValue -> fieldValue = newValue },
                    placeholder = { Text("Insert IP or Hostname") })

                Button(modifier = Modifier.fillMaxSize().weight(2f), shape = RectangleShape,
                    onClick = { /*TODO*/ }) {
                    Text(text = "Ping")
                }
            }
// Result
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().weight(8f)) {
                LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp).padding(bottom = 6.dp)
                    .background(Color.Black)
                    .padding(3.dp)
                    .background(Color.White)) {

                }
            }
        }
    }
}
