package com.example.recycleview_simple

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.activityViewModels
import android.content.res.Configuration

class ListFragment : Fragment() {
    private val vm: ItemViewModel by activityViewModels()
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Determine span count based on orientation
        val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 2 else 1

        rv.layoutManager = GridLayoutManager(this.context, spanCount)

        // Toggles favourites
        adapter = ItemAdapter(view.context,
            onItemClick = { item ->
                val intent = Intent(requireContext(), DetailView::class.java)
                intent.putExtra("item_key", item)
                startActivity(intent)
            },
            onFavouriteClick = { item ->
                vm.toggleFav(item)
            }
        )

        rv.adapter = adapter

        vm.items.observe(viewLifecycleOwner) { newPeople ->
            adapter.submitList(newPeople)
        }
    }
}