package com.example.iwatch.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.Entities.CommonItemSearch
import com.example.iwatch.R
import com.example.iwatch.ViewHolders.SearchViewHolder
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter(
    var commonItemList: ArrayList<CommonItemSearch>,
    val itemClickListener: OnSearchItemClickListener
) : RecyclerView.Adapter<SearchViewHolder>(), Filterable {

    var commonItemFilterList = ArrayList<CommonItemSearch>()

    init {
        commonItemFilterList = commonItemList
    }

    override fun getItemCount(): Int {
        return commonItemFilterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val commonItemSearch: CommonItemSearch = commonItemFilterList[position]
        holder.bind(commonItemSearch, itemClickListener)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    commonItemFilterList = commonItemList
                } else {
                    val resultList = ArrayList<CommonItemSearch>()
                    for (row in commonItemList) {
                        if (row.name?.toLowerCase(Locale.ROOT)?.contains(charSearch.toLowerCase(Locale.ROOT))!!) {
                            resultList.add(row)
                        }
                    }
                    commonItemFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = commonItemFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                commonItemFilterList = results?.values as ArrayList<CommonItemSearch>
                notifyDataSetChanged()
            }

        }
    }

}

interface OnSearchItemClickListener{
    fun onSearchItemClicked(commonItem: CommonItemSearch)
}