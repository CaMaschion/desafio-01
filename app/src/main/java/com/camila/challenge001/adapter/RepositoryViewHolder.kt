package com.camila.challenge001.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.camila.challenge001.R
import com.camila.challenge001.model.Item

class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val nameRepo: TextView = itemView.findViewById(R.id.nameRepo_txt)
    private val description: TextView = itemView.findViewById(R.id.description_txt)
    private val nameAuthor: TextView = itemView.findViewById(R.id.authorName_txt)
    private val avatar: ImageView = itemView.findViewById(R.id.avatar)
    private val forks: TextView = itemView.findViewById(R.id.forks_txt)
    private val stars: TextView = itemView.findViewById(R.id.stars_txt)


    fun bind(item: Item) {
        nameRepo.text = item.name
        description.text = item.description
        nameAuthor.text = item.owner.login
        forks.text = item.forks_count.toString()
        stars.text = item.stargazers_count.toString()
        Glide.with(itemView.context).load(item.owner.avatar_url).circleCrop().into(avatar)
    }
}