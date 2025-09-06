package com.example.recycleview_simple

import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        // Return true if they represent the same item (e.g., same ID or unique identifier)
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        // Return true if the contents are the same
        return oldItem == newItem
    }
}
