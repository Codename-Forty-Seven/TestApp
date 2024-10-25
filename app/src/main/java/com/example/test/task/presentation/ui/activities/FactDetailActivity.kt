package com.example.test.task.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.test.task.R
import com.example.test.task.databinding.ActivityFactDetailBinding
import com.example.test.task.presentation.model.FactUi

class FactDetailActivity : AppCompatActivity() {
    private val TAG = "FactDetailActivity"
    private lateinit var factBinding: ActivityFactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factBinding = DataBindingUtil.setContentView(this, R.layout.activity_fact_detail)

        val factText = intent.getStringExtra(EXTRA_FACT_TEXT)
        val factNum = intent.getIntExtra(EXTRA_FACT_NUM, 0)
        factBinding.fact = FactUi(factNum, factText!!)

    }


    companion object {
        const val EXTRA_FACT_TEXT = "extra_fact_text"
        const val EXTRA_FACT_NUM = "extra_fact_num"
    }
}