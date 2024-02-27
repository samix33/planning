package com.example.planning.ui.items

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.example.planning.data.Projectdata
import com.example.planning.ui.feature.project.ProjectViewModel
import com.example.planning.ui.theme.*
import dev.burnoo.cokoin.navigation.getNavViewModel
import kotlinx.coroutines.delay

@Composable
fun PreViewCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), color = backgroundMain
    ) {

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Dialog(
    onConfirm: () -> Unit,
    onDismis: () -> Unit,
) {
    val viewModel = getNavViewModel<ProjectViewModel>()
    val name = viewModel.name.observeAsState("")
    val detail = viewModel.detail.observeAsState("")
    val currentStep = remember { mutableStateOf(0) }

    Dialog(
        onDismissRequest = {
            onDismis()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = cardColor,
            modifier = Modifier
                .size(350.dp, 400.dp)
                .border(
                    1.dp, color = secondpriority,
                    RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                Text(
                    modifier = Modifier.padding(start = 255.dp).padding(bottom = 12.dp),
                    text = "اولویت کار",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                StepsProgressBar(
                    modifier = Modifier.fillMaxWidth(),
                    numberOfSteps = 2,
                    currentStep = currentStep.value
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(150.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 40.dp)
                ) {
                    Button(
                        onClick = {
                            currentStep.value--
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
                                  currentStep.value++
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
                Row(
                    modifier = Modifier.fillMaxSize(),
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
            placeholder = { Text(hint, color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            shape = Shapes.medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White)
        )
    }

}

@Composable
fun StepsProgressBar(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (step in 0..numberOfSteps) {
            Step(
                modifier = Modifier.weight(1F),
                isCompete = step < currentStep,
                isCurrent = step == currentStep
            )
        }
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
        numberOfSteps = 2,
        currentStep = currentStep.value
    )
}