package com.example.planning.ui.items.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.planning.ui.feature.notes.NotesViewModel
import com.example.planning.ui.theme.*
import dev.burnoo.cokoin.navigation.getNavViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogNotes(
    onConfirm: () -> Unit,
    onDismis: () -> Unit,
) {
    val viewModel = getNavViewModel<NotesViewModel>()
    val title = viewModel.title.observeAsState("")


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
                    title.value,
                    "موضوع یادداشت",
                ) {
                    viewModel.title.value = it
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
fun MainTextFieldNotes(
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
