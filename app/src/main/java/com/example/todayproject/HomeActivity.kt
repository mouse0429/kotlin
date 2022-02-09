package com.example.todayproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todayproject.adapter.TodoAdapter
import com.example.todayproject.dto.Todo
import com.example.todayproject.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity(), TodoEdit.onDataPassListener {
    var flag = 0
    lateinit var todoViewModel: TodoViewModel
    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoViewModel.todoList.observe(this) {
            todoAdapter.update(it)
        }

        todoAdapter = TodoAdapter(this)

        val rvTodoView = findViewById<RecyclerView>(R.id.todolist_view)
        rvTodoView.layoutManager = LinearLayoutManager(this)
        rvTodoView.adapter = todoAdapter

        todoAdapter.setItemCheckBoxClickListener(object: TodoAdapter.ItemCheckBoxClickListener {
            override fun onClick(view: View, position: Int, itemId: Long){
                CoroutineScope(Dispatchers.IO).launch{
                    val todo = todoViewModel.getOne(itemId)
                    todo.isChecked = !todo.isChecked
                    todoViewModel.update(todo)
                }
            }
        })

        var breakfastBtn = findViewById<Button>(R.id.breakfastBtn)
        var lunchBtn = findViewById<Button>(R.id.lunchBtn)
        var dinnerBtn = findViewById<Button>(R.id.dinnerBtn)

        var editBtn = findViewById<Button>(R.id.editBtn)

        editBtn.setOnClickListener {
            setFragment()
        }
        breakfastBtn.setOnClickListener {
            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra("index", 0)
            startActivity(intent)
        }
        lunchBtn.setOnClickListener {
            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra("index", 1)
            startActivity(intent)
        }
        dinnerBtn.setOnClickListener {
            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra("index", 2)
            startActivity(intent)
        }

    }

    override fun onDataPass(data: String?){
        var content = data
        var todo = content?.let { Todo(0, it, false) }
        CoroutineScope(Dispatchers.IO).launch {
            if (todo != null) {
                todoViewModel.insert(todo)
            }
        }
    }

    private fun setFragment() {
        val transaction=supportFragmentManager.beginTransaction()
        if (flag == 0) {
            transaction.add(R.id.todoEditFrame, TodoEdit(), "TodoEditTag")
            flag = 1
        } else {
            supportFragmentManager.findFragmentByTag("TodoEditTag")?.let { transaction.remove(it) }
            flag = 0
        }
        transaction.commit()
    }
}