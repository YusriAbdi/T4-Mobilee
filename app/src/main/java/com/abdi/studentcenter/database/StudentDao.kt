package com.abdi.studentcenter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abdi.studentcenter.database.StudentEntity

@Dao
interface StudentDao {

    @Insert
    suspend fun insert(student: StudentEntity)

    @Update
    suspend fun update(student: StudentEntity)

    @Delete
    suspend fun delete(student: StudentEntity)

    @Query("SELECT * FROM students ORDER BY createdAt DESC")
    fun getAllStudents(): LiveData<List<StudentEntity>>
}