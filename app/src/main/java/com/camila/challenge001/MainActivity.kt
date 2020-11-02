package com.camila.challenge001

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camila.challenge001.adapter.GithubAdapter
import com.camila.challenge001.data.ApiService
import com.camila.challenge001.data.GithubService
import com.camila.challenge001.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.camila.challenge001.model.GithubRepository as Repository

open class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var githubAdapter: GithubAdapter
    lateinit var progressBar: ProgressBar
    lateinit var repos : MutableList<Item>
    var pageNumber: Int = 1

    var isLoading: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        repos =  arrayListOf()

        getRepos(pageNumber)
        githubScroll()
    }

    private fun githubScroll() {

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                val total = githubAdapter.itemCount

                if (!isLoading) {

                    if ((visibleItemCount + lastVisibleItem ) >= total) {
                        pageNumber++
                        getRepos(pageNumber)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun getRepos(pageNumber: Int) {

        isLoading = true
        progressBar.visibility = View.VISIBLE

        val request = ApiService.buildService(GithubService::class.java)
        val call = request.getRepositories(pageNumber)

        call.enqueue(object : Callback<Repository> {

            override fun onResponse(
                call: Call<Repository>, response: Response<Repository>
            ) {

                if (response.isSuccessful) {

                    repos.addAll(response.body()!!.items)
                    githubAdapter = GithubAdapter(repos)

                    if(pageNumber > 1) {
                        val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                        githubAdapter.notifyItemInserted(lastVisibleItem);
                    }
                    else {

                        recyclerView.apply {
                            setHasFixedSize(true)
                            adapter = githubAdapter
                        }
                    }
                }
                isLoading = false
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<Repository>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
