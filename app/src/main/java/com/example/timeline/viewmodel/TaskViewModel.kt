package com.example.timeline.viewmodel

import androidx.lifecycle.*
import com.example.timeline.entities.Task
import com.example.timeline.services.repository.TaskRepository
import com.example.timeline.utils.enums.TaskType
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val lastTaskTypeSelected: MutableLiveData<TaskType> by lazy { MutableLiveData<TaskType>() }

    val allTasks: LiveData<List<Task>> = repository.allTasks.asLiveData()

    val countVisits: LiveData<Int> = repository.getSizeByTaskType(TaskType.VISIT).asLiveData()

    val countCalls: LiveData<Int> = repository.getSizeByTaskType(TaskType.CALL).asLiveData()

    val countMails: LiveData<Int> = repository.getSizeByTaskType(TaskType.MAIL).asLiveData()

    val countTasks: LiveData<Int> = repository.getSizeByTaskType(TaskType.TASKS).asLiveData()

    val countWorks: LiveData<Int> = repository.getSizeByTaskType(TaskType.WORKS).asLiveData()

    val countMore: LiveData<Int> = repository.getSizeByTaskType(TaskType.MORE).asLiveData()

    fun insert(task: Task) = viewModelScope.launch { repository.insert(task) }

  /*  fun deleteAll() = viewModelScope.launch { repository.deleteAll() }*/

}