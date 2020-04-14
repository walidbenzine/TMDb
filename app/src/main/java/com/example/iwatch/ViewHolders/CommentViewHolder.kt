package com.example.iwatch.ViewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.iwatch.R

class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val commentUserName = itemView.findViewById<TextView>(R.id.comment_user_name) as TextView
    val commentText = itemView.findViewById<TextView>(R.id.comment_text) as TextView
    val commentUserPicture = itemView.findViewById<ImageView>(R.id.comment_user_picture) as ImageView

}