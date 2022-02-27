package com.example.timeline.utils.functions

import com.example.timeline.R
import com.example.timeline.utils.enums.TaskType

class FunctionsHelper {
    companion object {
        fun getIconByTaskType(type: TaskType) = when(type) {
            TaskType.CALL -> R.drawable.ic_outline_call_24
            TaskType.MAIL -> R.drawable.ic_baseline_mail_outline_24
            TaskType.VISIT -> R.drawable.ic_outline_pin_drop_24
            TaskType.NONE -> R.drawable.ic_baseline_more_horiz_24
            TaskType.WORKS -> R.drawable.ic_baseline_work_outline_24
            TaskType.TASKS -> R.drawable.ic_outline_text_snippet_24
            TaskType.MORE -> R.drawable.ic_baseline_more_horiz_24
        }
    }
    //icones
}