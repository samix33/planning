package com.example.planning.ui.feature.detailProject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planning.data.Projectdata
import com.example.planning.db.ProjectDao
import kotlinx.coroutines.launch

class DetailProjectViewModel(
    private val projectDao: ProjectDao
) : ViewModel() {
    val name = MutableLiveData("")
    val detail = MutableLiveData("")
    val timeStart = MutableLiveData("")
    val timeEnd = MutableLiveData("")
    val progressbar = MutableLiveData("")
    val Priority = MutableLiveData("")


    fun insertOrUpdate(project: Projectdata) {
        viewModelScope.launch {
            val projectlist = arrayListOf<Projectdata>()
            projectlist.add(project)
            projectDao.insertOrUpdate(projectlist)
        }
    }

    fun addProject() {
        val data = Projectdata(
            name = name.value.toString(),
            detail = detail.value.toString(),
            Priority = Priority.value!!.toInt(),
            progressbar = progressbar.value!!.toInt(),
            timeStart = timeStart.value.toString(),
            timeEnd = timeEnd.value.toString(),
        )
        insertOrUpdate(data)
    }
}