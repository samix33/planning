package com.example.planning.ui.feature.detailProject

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.planning.ui.feature.project.ProjectScreen
import com.example.planning.ui.feature.project.ProjectViewModel
import com.example.planning.ui.theme.backgroundMain
import dev.burnoo.cokoin.navigation.getNavViewModel

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
            DetailsProjectScreen()

        }
    }
}
@Composable
fun DetailsProjectScreen() {
    val viewModel = getNavViewModel<DetailProjectViewModel>()


}