package com.example.planning.ui.feature.detailProject

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import com.example.planning.ui.feature.project.ProjectViewModel
import dev.burnoo.cokoin.navigation.getNavViewModel

@Composable
fun DetailsProjectScreen() {
    val viewModel = getNavViewModel<DetailProjectViewModel>()

}