package com.abdi.studentcenter.database

import com.abdi.studentcenter.database.StudentDao
import com.abdi.studentcenter.database.StudentEntity

class StudentRepository(private val dao: StudentDao) {

    val allStudents = dao.getAllStudents()

    suspend fun insert(student: StudentEntity) = dao.insert(student)
    suspend fun update(student: StudentEntity) = dao.update(student)
    suspend fun delete(student: StudentEntity) = dao.delete(student)
}