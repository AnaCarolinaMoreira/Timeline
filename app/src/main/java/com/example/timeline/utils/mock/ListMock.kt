package com.example.timeline.utils.mock

import com.example.timeline.R
import com.example.timeline.entities.MenuType
import com.example.timeline.utils.enums.TaskType

class ListMock {
    companion object {
        fun getMenuTypes(): ArrayList<MenuType> {
            val list = ArrayList<MenuType>()
            list.add(MenuType(TaskType.MAIL, R.drawable.ic_baseline_mail_outline_24))
            list.add(MenuType(TaskType.CALL, R.drawable.ic_outline_call_24))
            list.add(MenuType(TaskType.TASKS, R.drawable.ic_outline_text_snippet_24))
            list.add(MenuType(TaskType.WORKS, R.drawable.ic_baseline_work_outline_24))
            list.add(MenuType(TaskType.VISIT, R.drawable.ic_outline_pin_drop_24))
            list.add(MenuType(TaskType.MORE, R.drawable.ic_baseline_more_horiz_24))
            return list
        }
    }
}