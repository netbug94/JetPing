package com.example.jetping.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetping.engine_logic.JetPingViewModel
import com.example.jetping.ui.theme.ErgoGray
import com.example.jetping.ui.theme.ErgoWhite

@Preview(showBackground = true)
@Composable
fun MainScreenV(viewModel: JetPingViewModel = JetPingViewModel()) {

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
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().weight(1f).padding(vertical = 12.dp, horizontal = 5.dp)) {
                Button(modifier = Modifier.fillMaxSize().weight(2f), shape = RectangleShape,
                    onClick = { viewModel.fieldValue = "" }) {
                    Text(text = "Clear")
                }
                TextField(modifier = Modifier.fillMaxSize().weight(5f), value = viewModel.fieldValue, onValueChange = { newValue -> viewModel.fieldValue = newValue },
                    placeholder = { Text("Insert IP or Hostname") }
                )
                Button(modifier = Modifier.fillMaxSize().weight(2f), shape = RectangleShape,
                    onClick = { viewModel.performPing() }) {
                    Text(text = "Ping")
                }
            }
// Result
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().weight(8f)) {
                val lazyColumnState = rememberLazyListState()
                LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp).padding(bottom = 6.dp)
                    .background(ErgoGray)
                    .padding(3.dp)
                    .background(ErgoWhite),
                    state = lazyColumnState) {
                    itemsIndexed(viewModel.pingResults) { index, result ->
                        Text(text = result)
                        if (index < viewModel.pingResults.size - 1) {
                            HorizontalDivider(thickness = 1.dp, color = ErgoGray)
                        }
                    }
                }
                LaunchedEffect(viewModel.pingResults) {
                    val lastIndex = viewModel.pingResults.size - 1
                    if (lastIndex >= 0) {
                        lazyColumnState.animateScrollToItem(lastIndex)
                    }
                }
            }
        }
    }
}