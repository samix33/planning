package com.example.planning.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.planning.ui.feature.*
import com.example.planning.util.Mysreens

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
            NoteScreen()
        }
        composable(Mysreens.DetailNoteScreen.route){
            DetailNoteScreen()
        }
        composable(Mysreens.Planning.route){
            PlanningScreen()
        }
        composable(Mysreens.Setting.route){
            SettingScreen()
        }
        composable(Mysreens.Search.route){
            SearchScreen()
        }
    }

}