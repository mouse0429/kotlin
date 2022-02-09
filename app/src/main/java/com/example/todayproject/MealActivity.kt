package com.example.todayproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.todayproject.dto.Menu
import com.example.todayproject.viewmodel.MenuViewModel
import kotlinx.coroutines.*

class MealActivity : AppCompatActivity() {

    lateinit var menuViewModel: MenuViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        menuViewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        var animation = AnimationUtils.loadAnimation(this, R.anim.rotation)
        var image = findViewById<ImageView>(R.id.image)
        var index = intent.getIntExtra("index", 3)

        var randomBtn = findViewById<Button>(R.id.randomBtn)
        var backBtn = findViewById<Button>(R.id.backBtn)
        var menuText = findViewById<TextView>(R.id.menuText)
        var saveMenuBtn = findViewById<Button>(R.id.saveMenuBtn)
        var contentView = findViewById<EditText>(R.id.menuContent)

        var time = ""
        when(index) {
            0 -> { time = "breakfast" }
            1 -> { time = "lunch" }
            2 -> { time = "dinner" }
        }

//        Handler(Looper.getMainLooper()).postDelayed({
//            CoroutineScope(Dispatchers.Main).launch {
//                if(menuViewModel.getOne(time) == null){
//                    contentView.setText(null)
//                } else {
//                    contentView.setText(menuViewModel.getOne(time).content)
//                }
//            }
//        }, 500)

        randomBtn.setOnClickListener {
            image.startAnimation(animation)
            Handler(Looper.getMainLooper()).postDelayed({
                var randomIndex = (1..10).random()
                var randomMenu = arrayOf("탕수육", "짜장면", "짬뽕", "고추잡채", "오코노미야끼", "잔치국수", "고등어구이", "연어덮밥", "삼겹살", "스테이크")
                menuText.text = randomMenu.get(randomIndex)
            }, 3000)
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        saveMenuBtn.setOnClickListener {
            var content = contentView.text.toString()

            if (index != 3){
                var menu = Menu(content, "")
                when(index){
                    0 -> {
                        menu = Menu(content, "breakfast")
                        CoroutineScope(Dispatchers.IO).launch {
                            if (menuViewModel.getOne("breakfast") == null) {
                                menuViewModel.insert(menu)
                            } else {
                                menuViewModel.update(menu)
                            }
                        }
                    }
                    1 -> {
                        menu = Menu(content, "lunch")
                        CoroutineScope(Dispatchers.IO).launch {
                            if (menuViewModel.getOne("lunch") == null) {
                                menuViewModel.insert(menu)
                            } else {
                                menuViewModel.update(menu)
                            }
                        }
                    }
                    2 -> {
                        menu = Menu(content, "dinner")
                        CoroutineScope(Dispatchers.IO).launch {
                            if (menuViewModel.getOne("dinner") == null) {
                                menuViewModel.insert(menu)
                            } else {
                                menuViewModel.update(menu)
                            }
                        }
                    }
                }
            }

            Log.d("content", content)
            Log.d("index", index.toString())

        }
    }
}