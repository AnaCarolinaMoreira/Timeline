package com.example.timeline.view.activities

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.*
import com.example.timeline.view.adapters.TaskRecyclerViewAdapter
import com.example.timeline.view.dialog.AddTaskDialog
import com.example.timeline.viewmodel.TaskViewModelFactory
import com.example.timeline.R
import com.example.timeline.databinding.TimelineActivityBinding
import com.example.timeline.init.TimelineApplication
import com.example.timeline.viewmodel.*

class TimelineActivity : AppCompatActivity() {

    private lateinit var binding: TimelineActivityBinding

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TimelineApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TimelineActivityBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_TimelineAndroid)
        setContentView(binding.root)

        val recyclerAdapter = TaskRecyclerViewAdapter()
        val recyclerLayoutManager: RecyclerView.LayoutManager
        recyclerLayoutManager = LinearLayoutManager(this)

        binding.rvListTasks.layoutManager = recyclerLayoutManager
        binding.rvListTasks.adapter = recyclerAdapter

        setSupportActionBar(findViewById(R.id.toolbar))

        initObserverAllTasks(binding, recyclerAdapter)

        initViewAndAddViewModelListener()

        binding.fab.setOnClickListener { showDialog(taskViewModel) }

        ImageViewCompat.setImageTintList(
            binding.fab,
            ColorStateList.valueOf(ContextCompat.getColor(this, R.color.white)
        ))
    }

    private fun showDialog(
        taskViewModel: TaskViewModel
    ) = AddTaskDialog(
        taskViewModel
    ).show(supportFragmentManager, AddTaskDialog.TAG)

    private fun initObserverAllTasks(binding: TimelineActivityBinding, adapter: TaskRecyclerViewAdapter) {
        taskViewModel.allTasks.observe(this) { tasks ->
            tasks.let { adapter.submitList(it) }
            val params = binding.appBar.layoutParams
            val layoutSummary: LinearLayout = findViewById(R.id.layout_view_summary)
            if (tasks.isEmpty()) {
                binding.layoutEmptyList.viewEmptyList.visibility = View.VISIBLE
                layoutSummary.visibility = View.INVISIBLE
                params.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    100.toFloat(),
                    resources.displayMetrics
                ).toInt()
            } else {
                binding.layoutEmptyList.viewEmptyList.visibility = View.INVISIBLE
                layoutSummary.visibility = View.VISIBLE
                params.height = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    160.toFloat(),
                    resources.displayMetrics
                ).toInt()
            }
        }
    }
    private fun initViewAndAddViewModelListener() {
        val txtVisits = findViewById<TextView>(R.id.txt_visits)
        val txtCalls = findViewById<TextView>(R.id.txt_calls)
        val txtMails = findViewById<TextView>(R.id.txt_mails)
        val txtTasks = findViewById<TextView>(R.id.txt_tasks)
        val txtWorks = findViewById<TextView>(R.id.txt_works)
        val txtMore = findViewById<TextView>(R.id.txt_more)

        val layoutViewVisits = findViewById<LinearLayout>(R.id.layoutViewVisits)
        val layoutViewCalls = findViewById<LinearLayout>(R.id.layoutViewCalls)
        val layoutViewMails = findViewById<LinearLayout>(R.id.layoutViewMails)
        val layoutViewTasks = findViewById<LinearLayout>(R.id.layoutViewTasks)
        val layoutViewWorks = findViewById<LinearLayout>(R.id.layoutViewWorks)
        val layoutViewMore = findViewById<LinearLayout>(R.id.layoutViewMore)

        taskViewModel.countVisits.observe(this) {
            count -> setTaskSize(txtVisits, layoutViewVisits, count)
        }

        taskViewModel.countCalls.observe(this) {
            count -> setTaskSize(txtCalls, layoutViewCalls, count)
        }

        taskViewModel.countMails.observe(this) {
            count ->  setTaskSize(txtMails, layoutViewMails, count)
        }

        taskViewModel.countTasks.observe(this) {
            count -> setTaskSize(txtTasks, layoutViewTasks, count)
        }

        taskViewModel.countWorks.observe(this) {
            count -> setTaskSize(txtWorks, layoutViewWorks, count)
        }

        taskViewModel.countMore.observe(this) {
            count -> setTaskSize(txtMore, layoutViewMore, count)
        }
    }

    private fun setTaskSize(txtView: TextView, layout: LinearLayout, size: Int) {
        if (size > 0) {
            txtView.text = size.toString()
            layout.visibility = View.VISIBLE
            val params = layout.layoutParams
            params.width = LinearLayout.LayoutParams.WRAP_CONTENT
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        } else {
            layout.visibility = View.INVISIBLE
            val params = layout.layoutParams
            params.width = 0
            params.height = 0
        }
    }
}