package com.example.planning.ui.feature

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planning.R
import com.example.planning.ui.theme.Progressbar
import com.example.planning.ui.theme.backgroundMain
import com.example.planning.ui.theme.cardColor
import com.example.planning.ui.theme.firstpriority

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProgressCard(
    progress : String,
    text  :String,
    navigateToDetail:() -> Unit
) {
    val Progress by remember {
        mutableStateOf(0.5f)
    }
    val animatedProgressBar by animateFloatAsState(
        targetValue = Progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            shape = RoundedCornerShape(16.dp),
            backgroundColor = cardColor,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {

            Box(modifier = Modifier.fillMaxSize()) {


                Card(

                    backgroundColor = cardColor,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .fillMaxSize()
                        .rotate(180f)
                        .padding(vertical = 30.dp),
                    elevation = 0.dp,
                    shape = RoundedCornerShape(16.dp),

                    border = BorderStroke(
                        8.dp,
                        Brush.horizontalGradient(
                            colors = listOf(
                                firstpriority.copy(alpha = 0.8f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent
                            ),
                            startX = 0f, endX = 30f,
                        )
                    )
                ) {
                    Box(
                        modifier = Modifier.background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    firstpriority.copy(alpha = 0.2f),
                                    Color.Transparent
                                ), startX = 0f , endX = 40f
                            )
                        )
                    )
                }
                Column(modifier = Modifier.padding(start = 50.dp, top = 25.dp, bottom = 25.dp)) {
                    Text(
                        modifier = Modifier
                            .weight(0.2f).padding(top = 10.dp),
                        text = text,
                        color = Color.White,
                        style = TextStyle(fontSize = 18.sp),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .weight(0.2f)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "پیشرفت",
                            color = Color.White,
                            style = TextStyle(fontSize = 14.sp),
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            modifier = Modifier.padding(end = 20.dp),
                            text = "${progress}%",
                            color = Progressbar,
                            style = TextStyle(fontSize = 14.sp),
                            fontWeight = FontWeight.Medium
                        )

                    }
                    Card(
                        shape = RoundedCornerShape(3.dp),
                        backgroundColor = Color.Transparent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp)
                    ) {
                        LinearProgressIndicator(

                            progress = animatedProgressBar, color = Progressbar,
                        )
                    }



                    Row(
                        modifier = Modifier.weight(0.2f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column() {

                        }
                        Card(
                            shape = CircleShape,
                            backgroundColor = backgroundMain,
                            modifier = Modifier
                                .size(32.dp),
                            onClick = {navigateToDetail.invoke()}
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_next),
                                contentDescription = null,
                                tint = Progressbar,
                                modifier = Modifier
                                    .padding(9.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreViewCard() {
    androidx.compose.material.Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp), color = backgroundMain
    ) {
        ProgressCard("test" , "10"){

        }

    }
}