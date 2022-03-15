package com.example.recyclerview_pagination

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerview_pagination.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var profileList: ArrayList<ProfileModel>
    lateinit var profileViewModel: ProfileViewModel
    private var retrofit: Retrofit? = null
    lateinit var profileAdapter: ProfileAdapter
    var page = 1
    var limit = 5

    lateinit var mainInterface: MainInterface

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileList = ArrayList()
        profileAdapter = ProfileAdapter(profileList);
        binding.rvProfileList.layoutManager = LinearLayoutManager(this)
        binding.rvProfileList.adapter = profileAdapter

        profileViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProfileViewModel::class.java]

        getData(page, limit)

        binding.scrollView.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, _: Int, _: Int ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                page++
                binding.progressBar.visibility = View.VISIBLE
                getData(page, limit)
            }
        }

    }


    private fun getData(page: Int, limit: Int) {
        profileViewModel.getListCoroutine(page,limit)
        profileViewModel.allWorkesCoroutine.observe(this) {
            Log.d(
                "ShowlistActivity", "Data Observed: "
            )
            it?.let { it1 -> profileList.addAll(it1) }
            profileAdapter.notifyDataSetChanged()
        }
    }
}