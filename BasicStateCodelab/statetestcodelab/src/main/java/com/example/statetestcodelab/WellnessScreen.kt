package com.example.statetestcodelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    StatefulCounter(modifier)
}

@Composable
private fun StatefulCounter(
    modifier: Modifier,
    viewModel: WellnessViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    var waterCount by rememberSaveable { mutableStateOf(0) }
    Column(modifier = modifier) {
        WaterCounter(waterCount, { waterCount++ })
        WellnessTasksList(
            tasks = viewModel.tasks,
            onCloseTask = { task -> viewModel.remove(task) },
            onCheckedTask = { task, checked -> viewModel.changeTaskChecked(task, checked) }
        )
    }
}

@Composable
fun WaterCounter(waterCount: Int, waterCountIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column (modifier = modifier.padding(16.dp)) {
        if (waterCount > 0) {
            Text(
                text = "You've had $waterCount glasses of water today",
            )
        }
        Button(
            onClick = waterCountIncrement,
            enabled = waterCount < 10,
        ) {
            Text(text = "Add one")
        }
    }
}
