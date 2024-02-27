package com.example.planning.ui.feature.project

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planning.data.Projectdata
import com.example.planning.db.ProjectDao
import kotlinx.coroutines.launch

class ProjectViewModel(
    private val projectDao: ProjectDao
) : ViewModel() {
    var projectdata = MutableLiveData<List<Projectdata>>()
    val name = MutableLiveData("")
    val detail = MutableLiveData("")

    fun insertOrUpdate(project: Projectdata) {
        viewModelScope.launch {
            val projectlist = arrayListOf<Projectdata>()
            projectlist.add(project)
            projectDao.insertOrUpdate(projectlist)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            projectdata.value = projectDao.getAll()

        }
    }

    fun delethestory(project: Projectdata) {
        viewModelScope.launch {
            projectDao.deletProject(project)

        }
    }
    fun delethestoryall() {
        viewModelScope.launch {
            projectDao.deleteAll()

        }
    }

        fun addProject() {
            val data = Projectdata(
                name = name.value.toString(),
                detail = detail.value.toString(),
                Priority = "بالا",
                progressbar = 0,
                timeStart = "",
                timeEnd = "",
            )
            insertOrUpdate(data)
        }
    }