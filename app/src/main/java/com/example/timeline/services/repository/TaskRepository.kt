package com.example.timeline.services.repository

import androidx.annotation.WorkerThread
import com.example.timeline.entities.Task
import com.example.timeline.services.dao.ITaskDAO
import com.example.timeline.utils.enums.TaskType
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val iTaskDAO: ITaskDAO) {
    val allTasks: Flow<List<Task>> = iTaskDAO.getTasks()

    @WorkerThread
    suspend fun insert(task: Task) = iTaskDAO.insert(task)

    @WorkerThread
    suspend fun deleteAll() = iTaskDAO.deleteAll()

    fun getSizeByTaskType(type: TaskType): Flow<Int> = iTaskDAO.getSizeByType(type)
}