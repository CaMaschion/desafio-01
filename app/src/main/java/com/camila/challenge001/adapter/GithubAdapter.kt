package com.camila.challenge001.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.camila.challenge001.R
import com.camila.challenge001.model.Item


class GithubAdapter(private val repository: List<Item>) : RecyclerView.Adapter<RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repository.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        return holder.bind(repository[position])
    }
}

