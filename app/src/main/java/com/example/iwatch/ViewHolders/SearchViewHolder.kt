package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Adapters.OnSearchItemClickListener
import com.example.iwatch.Entities.CommonItemSearch
import com.example.iwatch.R

class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val searchItemText = itemView.findViewById<TextView>(R.id.search_item_text) as TextView

    fun bind(commonItemSearch: CommonItemSearch, clickListener: OnSearchItemClickListener){
        searchItemText.text = commonItemSearch.name

        itemView.setOnClickListener {
            clickListener.onSearchItemClicked(commonItemSearch)
        }
    }

}