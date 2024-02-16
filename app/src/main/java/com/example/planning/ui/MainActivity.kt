package com.example.planning.ui

import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Bottom
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planning.ui.feature.MainScreen
import com.example.planning.ui.feature.NotProjectView
import com.example.planning.ui.feature.ProjectScreen
import com.example.planning.ui.theme.*
import com.example.planning.util.Mysreens
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanningUi()
        }
    }
}
@Composable
fun PlanningUi() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Mysreens.MainScreen.route){
        composable(Mysreens.MainScreen.route){
            MainScreen()
        }
        composable(Mysreens.ProjectScreen.route){
            ProjectScreen()
        }
        composable(Mysreens.DetailsProjectScreen.route){
            DetailsProjectScreen()
        }
        composable(Mysreens.NotesScreen.route){
            NotesScreen()
        }
    }

}

fun NotesScreen() {

}

@Composable
fun DetailsProjectScreen() {
}






