package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val commentText = itemView.findViewById<TextView>(R.id.comment_text) as TextView

}