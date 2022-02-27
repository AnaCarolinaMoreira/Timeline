package com.example.timeline.init

import android.app.Application
import com.example.timeline.services.database.TaskRoomDatabase
import com.example.timeline.services.repository.TaskRepository
import kotlinx.coroutines.*

class TimelineApplication : Application() {
    val database by lazy { TaskRoomDatabase.getDatabase(this)}
    val repository by lazy { TaskRepository(database.taskDAO()) }
}