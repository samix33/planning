package com.example.planning.ui.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planning.ui.theme.cardColor
import com.example.planning.ui.theme.textcolor


@Composable
fun NotesItems(
    title : String,
    Detail :String
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Card(
            shape = RoundedCornerShape(8.dp),
            backgroundColor = cardColor,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(400.dp, 270.dp)
                .padding(top = 18.dp)
        ) {
            Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(all = 16.dp)
                )
                Text(
                    text = Detail,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textcolor,
                    modifier = Modifier.padding(start = 18.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis

                )
            }
        }
    }
}
