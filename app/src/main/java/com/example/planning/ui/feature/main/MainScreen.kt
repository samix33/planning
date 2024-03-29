package com.example.planning.ui.feature.main

import android.app.AlertDialog
import android.util.Log
import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planning.ui.feature.notes.NoteScreen
import com.example.planning.ui.feature.project.ProjectScreen
import com.example.planning.ui.feature.project.ProjectViewModel
import com.example.planning.ui.items.dialog.DialogNotes
import com.example.planning.ui.items.dialog.DialogProject
import com.example.planning.ui.theme.Progressbar
import com.example.planning.ui.theme.backgroundMain
import com.example.planning.ui.theme.textcolor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
@Preview
@Composable
fun MainScreen() {
    val page1 = remember {
        mutableStateOf(0)
    }
    val scope = rememberCoroutineScope()

    val viewModel = getNavViewModel<MainViewModel>()
    val viewModelProject = getNavViewModel<ProjectViewModel>()
    Scaffold(floatingActionButtonPosition = FabPosition.Center, floatingActionButton = {
        BottomAdd {
            viewModel.onByClickt()

        }
    }) {
        Surface(modifier = Modifier.fillMaxSize(), color = backgroundMain) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp)
            ) {
                Toptolbar()
                Spacer(Modifier.size(16.dp))
                SimpleTabLayout(page1)
                if (viewModel.isDialogShown) {
                    if (page1.value == 0) {
                        DialogProject(onConfirm = {
                            viewModelProject.addProject()
                            scope.launch {
                                delay(250)
                                viewModelProject.getAll()
                            }
                            viewModel.onDismisDialog()

                        }, onDismis = {
                            viewModel.onDismisDialog()

                        })

                    } else if (page1.value == 2) {
                        DialogNotes(onConfirm = {
                            viewModel.onDismisDialog()

                        }, onDismis = {
                            viewModel.onDismisDialog()

                        })
                    } else {
                        DialogNotes(onConfirm = {
                            viewModel.onDismisDialog()

                        }, onDismis = {
                            viewModel.onDismisDialog()

                        })
                    }


                }
            }
        }
    }
}


@Composable
fun Toptolbar() {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = backgroundMain,
        title = {
            Text(
                text = "برنامه ریزی",
                color = Color.White,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )

            }
        }

    )
}


@Composable
@OptIn(ExperimentalPagerApi::class)
fun SimpleTabLayout(page1: MutableState<Int> ) {
    val tabitem = listOf("کار ها", "برنامه ریزی", "یادداشت ها")
    val pagestate = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Surface(color = backgroundMain) {
        Box {
            Column {
                Card(
                    shape = RoundedCornerShape(38.dp),
                    backgroundColor = textcolor,
                    modifier = Modifier.padding(end = 24.dp, start = 24.dp)
                ) {
                    TabRow(
                        selectedTabIndex = pagestate.currentPage,
                        backgroundColor = backgroundMain,
                        modifier = Modifier
                            .padding(all = 1.dp)
                            .background(color = Color.Transparent)
                            .clip(RoundedCornerShape(30.dp)),
                        indicator = { tabPositions ->
                            TabRowDefaults.Indicator(
                                Modifier
                                    .pagerTabIndicatorOffset(pagestate, tabPositions)
                                    .width(0.dp)
                                    .height(0.dp)
                            )
                        }
                    ) {
                        tabitem.forEachIndexed { index, title ->
                            val color = remember {
                                Animatable(Progressbar)
                            }
                            LaunchedEffect(pagestate.currentPage == index) {
                                color.animateTo(if (pagestate.currentPage == index) Progressbar else backgroundMain)

                            }
                            Tab(
                                text = {
                                    Text(
                                        title,
                                        style = if (pagestate.currentPage == index)
                                            TextStyle(
                                                color = Color.White,
                                                fontSize = 18.sp
                                            )
                                        else TextStyle(
                                            color = Color.White,
                                            fontSize = 16.sp
                                        )
                                    )
                                },
                                selected = pagestate.currentPage == index,
                                modifier = Modifier.background(
                                    color = color.value,
                                    shape = RoundedCornerShape(30.dp)
                                ),
                                onClick = {
                                    coroutineScope.launch {
                                        pagestate.animateScrollToPage(index)
                                    }
                                })


                        }
                    }
                }
                HorizontalPager(
                    count = tabitem.size,
                    state = pagestate,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundMain)
                ) { page ->
                    page1.value = pagestate.currentPage

                    if (tabitem[page] == "کار ها") {
                        ProjectScreen()
                    } else if (tabitem[page] == "برنامه ریزی") {
                    } else {
                        NoteScreen()

                    }

                }

            }
        }

    }
}

@Composable
fun BottomAdd(onclick: () -> Unit) {
    FloatingActionButton(
        shape = RoundedCornerShape(38.dp),
        backgroundColor = Progressbar,
        modifier = Modifier.size(95.dp, 40.dp),
        onClick = onclick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = "اضافه کردن",
                    color = Color.White,
                    style = TextStyle(fontSize = 14.sp),
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

            }

        }


    }
}

@Composable
fun NotProjectView() {
    Surface(modifier = Modifier.size(650.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundMain),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "شما هنوز پروژه ای ندارید",
                color = Color.White,
                style = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }

    }


}