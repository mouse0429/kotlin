package com.example.todayproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.todayproject.dto.Todo

class TodoEdit : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    interface onDataPassListener {
        fun onDataPass(data: String?)
    }

    lateinit var dataPassListener: onDataPassListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as onDataPassListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_todo_edit, container, false)
        val saveBtn: Button = view.findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener {
            var contentView: EditText = view.findViewById(R.id.todoContent)
            var content = contentView.text.toString()
            dataPassListener.onDataPass(content)
            contentView.setText(null)
        }

        return view
    }
}