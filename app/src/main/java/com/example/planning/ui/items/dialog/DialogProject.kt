package com.example.planning.ui.items.dialog

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.planning.ui.feature.project.ProjectViewModel
import com.example.planning.ui.theme.*
import dev.burnoo.cokoin.navigation.getNavViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogProject(
    onConfirm: () -> Unit,
    onDismis: () -> Unit,
) {
    val context = LocalContext.current
    val currentStep = remember { mutableStateOf(0) }
    var progress by remember { mutableStateOf(0f) }

    val viewModel = getNavViewModel<ProjectViewModel>()
    val name = viewModel.name.observeAsState("")
    val detail = viewModel.detail.observeAsState("")
    var priorityColor by remember {
        mutableStateOf(firstpriority)
    }

    when (currentStep.value) {
        0 -> {
            progress = 0.33f
            priorityColor = firstpriority
        }
        1 -> {
            progress = 0.66f
            priorityColor = secondpriority
        }
        2 -> {
            progress = 1f
            priorityColor = Thirdpriority

        }

    }

    val size by animateFloatAsState(
        targetValue = progress,
        tween(
            durationMillis = 1000,
            delayMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )
    Log.v("prior", currentStep.value.toString())
    Dialog(
        onDismissRequest = {
            onDismis()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(0.5.dp, color = priorityColor.copy(alpha = 0.5f)),

            backgroundColor = cardColor,



        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp),
            ) {
                MainTextField(
                    name.value,
                    "نام پروژه",
                ) {
                    viewModel.name.value = it
                }
                MainTextField(
                    detail.value,
                    "توضیحات پروژه",

                    ) {
                    viewModel.detail.value = it
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    modifier = Modifier
                        .padding(start = 255.dp)
                        .padding(bottom = 12.dp),
                    text = "اولویت کار",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                StepsProgressBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    size = size,
                    priorityColor = priorityColor
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(150.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 40.dp , top = 15.dp , bottom = 25.dp)
                ) {
                    Button(
                        onClick = {
                            if (currentStep.value > 0) {
                                currentStep.value -= 1
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = firstpriority),
                        modifier = Modifier
                            .size(45.dp, 45.dp),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "<",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {
                            if (currentStep.value < 2) {
                                currentStep.value += 1
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = firstpriority),
                        modifier = Modifier
                            .size(45.dp, 45.dp),
                        shape = CircleShape
                    ) {
                        Text(
                            text = ">",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onDismis()
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = firstpriority),
                        modifier = Modifier
                            .fillMaxWidth()

                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "کنسل",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.priority.value = currentStep.value
                            onConfirm()

                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = firstpriority),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "تایید",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = Color.White

                        )
                    }

                }
            }
        }

    }
}

@Composable
fun MainTextField(
    edtValue: String,
    hint: String,
    onValueChange: (String) -> Unit
) {
    MaterialTheme(darkColors()) {
        OutlinedTextField(
            label = { Text(hint, color = Color.White) },
            value = edtValue,
            singleLine = true,
            onValueChange = onValueChange,
            placeholder = { Text(hint, color = Color.Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .padding(horizontal = 10.dp)
            ,
            shape = Shapes.medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White)
        )
    }

}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, size: Float, priorityColor: Color) {


    Row(
        modifier = Modifier
            .widthIn(min = 30.dp)
            .fillMaxWidth(size),
        horizontalArrangement = Arrangement.End
    ) {
    }
    // Progress Bar
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
    ) {
        // for the background of the ProgressBar
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(9.dp))
                .background(Color.LightGray.copy(alpha = 0.9f))
        )
        // for the progress of the ProgressBar
        Box(
            modifier = Modifier
                .fillMaxWidth(size)
                .fillMaxHeight()
                .clip(RoundedCornerShape(9.dp))
                .background(priorityColor)
                .animateContentSize()
        )
    }


}

@Composable
fun Step(modifier: Modifier = Modifier, isCompete: Boolean, isCurrent: Boolean) {
    val color = if (isCompete || isCurrent) Progressbar else Color.LightGray
    val innerCircleColor = if (isCompete) secondpriority else Color.LightGray

    Box(modifier = modifier) {

        //Line
        Divider(
            modifier = Modifier.align(Alignment.CenterStart),
            color = color,
            thickness = 2.dp
        )

        //Circle
        Canvas(modifier = Modifier
            .size(15.dp)
            .align(Alignment.CenterEnd)
            .border(
                shape = CircleShape,
                width = 2.dp,
                color = color
            ),
            onDraw = {
                drawCircle(color = innerCircleColor)
            }
        )
    }
}

@Preview
@Composable
fun StepsProgressBarPreview() {
    val currentStep = remember { mutableStateOf(0) }
    StepsProgressBar(
        modifier = Modifier.fillMaxWidth(),
        size = 1f,
        priorityColor = Progressbar
    )
}