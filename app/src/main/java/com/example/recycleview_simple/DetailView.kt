package com.example.recycleview_simple

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.getValue

class DetailView : AppCompatActivity() {
    private val vm: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root2)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            view.setBackgroundColor(android.graphics.Color.TRANSPARENT)
            insets
        }



        val linearLayout = findViewById<LinearLayout>(R.id.mainLayout)
        linearLayout.orientation = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayout.HORIZONTAL
        } else {
            LinearLayout.VERTICAL
        }

        val imageView = findViewById<ImageView>(R.id.itemImage)
        val itemName = findViewById<TextView>(R.id.nameTextView)

        val item = intent.getParcelableExtra<Item>("item_key") ?: Item()

        imageView.setImageResource(item.imageSrc)
        itemName.text = item.name

    }
}
