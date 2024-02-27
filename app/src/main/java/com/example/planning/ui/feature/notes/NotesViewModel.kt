package com.example.planning.ui.feature.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planning.data.Notesdata
import com.example.planning.data.Projectdata
import com.example.planning.db.NotesDao
import kotlinx.coroutines.launch

class NotesViewModel (private val notesDao: NotesDao): ViewModel() {
    var notestdata = MutableLiveData<List<Notesdata>>()
    val detail = MutableLiveData("")
    val title = MutableLiveData("")

    fun insertOrUpdate(project: Notesdata) {
        viewModelScope.launch {
            val projectlist = arrayListOf<Notesdata>()
            projectlist.add(project)
            notesDao.insertOrUpdate(projectlist)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            notestdata.value = notesDao.getAll()

        }
    }

    fun delethestory(project: Notesdata) {
        viewModelScope.launch {
            notesDao.deletProject(project)

        }
    }

    fun delethestoryall() {
        viewModelScope.launch {
            notesDao.deleteAll()

        }
    }

    fun addProject() {

    }
}