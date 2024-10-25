package com.example.test.task.presentation.ui.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.task.presentation.model.FactUi
import com.example.test.task.databinding.SingleNumberItemBinding
import com.example.test.task.presentation.ui.activities.FactDetailActivity

class NumberAdapter(private var factUis: List<FactUi>) :
    RecyclerView.Adapter<NumberAdapter.NumberViewHolder>() {
private val TAG = "NumberAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val binding =
            SingleNumberItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return factUis.size
    }


    fun updateFacts(newFactUis: List<FactUi>) {
        factUis = newFactUis
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val selectedFact = factUis[position]
        holder.bind(selectedFact)


        holder.itemView.setOnClickListener {
            Log.d(TAG, "selectedFact: $selectedFact")
            val context = holder.itemView.context
            val intent = Intent(context, FactDetailActivity::class.java)
            intent.putExtra(FactDetailActivity.EXTRA_FACT_TEXT, selectedFact.fact)
            intent.putExtra(FactDetailActivity.EXTRA_FACT_NUM, selectedFact.number)
            context.startActivity(intent)
        }
    }

    class NumberViewHolder(
        private val binding: SingleNumberItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(numberData: FactUi?) {
            with(binding) {
                numberData?.let {
                    tvRandomNumber.text = it.number.toString()
                    tvRandomFact.text = it.fact
                }
            }
        }
    }
}