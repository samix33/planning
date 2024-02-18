package com.example.planning.ui.feature.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planning.ui.theme.backgroundMain

@Preview
@Composable
fun Preview() {
    Surface(modifier = Modifier.fillMaxSize(), color = backgroundMain) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            ProjectScreen()
        }
    }
}

@Composable
fun ProjectScreen() {
    Surface(modifier = Modifier.size(850.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundMain),
        ) {


        }

    }


}