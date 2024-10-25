package com.example.test.task.presentation.ui.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.task.R
import com.example.test.task.data.local.NumberDatabase
import com.example.test.task.data.remote.RetrofitInstance
import com.example.test.task.data.repository.NumberRepositoryImpl
import com.example.test.task.databinding.ActivityMainBinding
import com.example.test.task.presentation.mapper.FactUiMapper
import com.example.test.task.presentation.ui.adapter.NumberAdapter
import com.example.test.task.presentation.ui.viewModel.MainViewModel
import com.example.test.task.presentation.ui.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            NumberRepositoryImpl(
                NumberDatabase.getDatabase(this).factDao(),
                RetrofitInstance.retrofit
            ),
            FactUiMapper()
        )
    }
    private lateinit var adapter: NumberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)


        adapter = NumberAdapter(emptyList())
        mainBinding.rvMain.layoutManager = LinearLayoutManager(this)
        mainBinding.rvMain.adapter = adapter


        mainViewModel.facts.observe(this) { facts ->
            if (facts != null && facts.isNotEmpty())
                adapter.updateFacts(facts)
        }

        mainViewModel.fact.observe(this) { fact ->
            if (fact != null) {
                mainViewModel.insertFact(fact)
                mainBinding.mainFragment.edTxtNumber.setText("")
            }
        }

        with(mainBinding) {
            mainFragment.btnSearch.setOnClickListener {
                if (!isOnline()) return@setOnClickListener
                val number = mainFragment.edTxtNumber.text.toString().toIntOrNull()
                if (number != null) {
                    mainViewModel.getFactForNumber(number)
                }
            }

            mainFragment.btnGetRandom.setOnClickListener {
                if (!isOnline()) return@setOnClickListener
                mainViewModel.getRandomMathFact()
            }
        }

        mainViewModel.getAllFacts()
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)

        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}