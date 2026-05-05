package com.abdi.studentcenter.database

import android.app.Application
import androidx.lifecycle.*
import com.abdi.studentcenter.database.StudentEntity
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: StudentRepository
    val allStudents: LiveData<List<StudentEntity>>

    init {
        val dao = AppDatabase.getDatabase(application).studentDao()
        repo = StudentRepository(dao)
        allStudents = repo.allStudents
    }

    fun insert(student: StudentEntity) = viewModelScope.launch {
        repo.insert(student)
    }

    fun delete(student: StudentEntity) = viewModelScope.launch {
        repo.delete(student)
    }

    fun update(student: StudentEntity) = viewModelScope.launch {
        repo.update(student)
    }
}