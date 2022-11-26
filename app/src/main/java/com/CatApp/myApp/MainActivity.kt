package com.plcoding.retrofitcrashcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.plcoding.retrofitcrashcourse.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var catAdapter: CatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getCats()
            } catch(e: IOException) {
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                binding.progressBar.isVisible = false
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                catAdapter.cats = response.body()!!
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun setupRecyclerView() = binding.RV.apply {
        catAdapter = CatAdapter()
        adapter = catAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }
}