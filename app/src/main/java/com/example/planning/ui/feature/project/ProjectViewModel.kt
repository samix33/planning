package com.example.planning.ui.feature.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planning.data.Projectdata
import com.example.planning.db.ProjectDao

class ProjectViewModel(
    private val projectDao: ProjectDao
) : ViewModel() {
    var projectdata = MutableLiveData<List<Projectdata>>()


    suspend fun insertOrUpdate(project: Projectdata) {
        val projectlist = arrayListOf<Projectdata>()
        projectlist.add(project)
        projectDao.insertOrUpdate(projectlist)
    }

    suspend fun getAll()  {
        projectdata.value = projectDao.getAll()
    }

    suspend fun delethestory(project: Projectdata) {
        projectDao.deletProject(project)
    }

}