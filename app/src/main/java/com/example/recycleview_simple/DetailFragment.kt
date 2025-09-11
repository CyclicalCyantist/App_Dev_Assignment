package com.example.recycleview_simple

import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.widget.TextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import kotlin.getValue

class DetailFragment : Fragment() {
    private val vm: ItemViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.root2)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            view.setBackgroundColor(android.graphics.Color.TRANSPARENT)
            insets
        }

        val linearLayout = view.findViewById<LinearLayout>(R.id.mainLayout)
        linearLayout.orientation = LinearLayout.HORIZONTAL

        val imageView = view.findViewById<ImageView>(R.id.itemImage)
        val itemName = view.findViewById<TextView>(R.id.nameTextView)

        val favBtn = view.findViewById<ImageButton>(R.id.btnFav)

        vm.selectedItemId.observe(viewLifecycleOwner) { id ->
            val item = vm.getSelectedItem()
            if (item != null) {
                imageView.setImageResource(item.imageSrc)
                itemName.text = item.name
                favBtn.setImageResource(if (item.isFavourite) R.drawable.one_star_icon2 else R.drawable.one_star_outline_icon2)
            }
        }

        favBtn.setOnClickListener {
            vm.getSelectedItem()?.let { vm.toggleFav(it) }
            val item: Item? = vm.getSelectedItem()
            if (item != null) {
                if (item.isFavourite) {
                    favBtn.setImageResource(R.drawable.one_star_icon2)
                } else {
                    favBtn.setImageResource(R.drawable.one_star_outline_icon2)
                }
            }
        }

        val backBtn = view.findViewById<ImageButton>(R.id.backBtn)
        backBtn.setOnClickListener {
            vm.clearCurrentItem()
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}
