package com.example.planning.ui.feature.project

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planning.ui.feature.detailProject.DetailProjectViewModel
import com.example.planning.ui.items.ProgressCard
import com.example.planning.ui.theme.backgroundMain
import com.example.planning.util.Mysreens
import dev.burnoo.cokoin.navigation.getNavController
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
            ProjectScreen()

        }
    }
}

    @Composable
    fun ProjectScreen() {
        val viewModel = getNavViewModel<ProjectViewModel>()
        val viewModelDetail = getNavViewModel<DetailProjectViewModel>()

        viewModel.getAll()
        val data = viewModel.projectdata.observeAsState().value
        val priority = viewModel.priority.observeAsState().value


        val navigation = getNavController()

        Surface(modifier = Modifier.size(650.dp), color = backgroundMain) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 50.dp)
            ) {
                if (data != null) {
                    items(data) {
                        ProgressCard(it) {
                            viewModelDetail.name.value = it.name
                            viewModelDetail.detail.value = it.detail
                            navigation.navigate(Mysreens.DetailsProjectScreen.route)

                        }
                    }
                }
            }
        }

    }
