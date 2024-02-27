package com.example.planning.di


import androidx.room.Room
import com.example.planning.db.AppDatabase
import com.example.planning.ui.feature.Planning.PlanningViewModel
import com.example.planning.ui.feature.detailNotes.DetailNotesViewModel
import com.example.planning.ui.feature.detailProject.DetailProjectViewModel
import com.example.planning.ui.feature.main.MainViewModel
import com.example.planning.ui.feature.notes.NotesViewModel
import com.example.planning.ui.feature.project.ProjectViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules   = module {

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_dataBase.db").build() }
    viewModel{DetailNotesViewModel()}
    viewModel{DetailProjectViewModel(get<AppDatabase>().projectDao())}
    viewModel{NotesViewModel(get<AppDatabase>().notesDao())}
    viewModel{PlanningViewModel()}
    viewModel{ MainViewModel() }
    viewModel{ProjectViewModel( get<AppDatabase>().projectDao())}

}