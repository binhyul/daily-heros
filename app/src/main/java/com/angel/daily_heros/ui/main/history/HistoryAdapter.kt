package com.angel.daily_heros.ui.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.HistoryItemBinding


internal class HistoryAdapter :
    ListAdapter<HistoryModel, HistoryModelViewHolder>(
        HistoryModelDiff
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryModelViewHolder {
        return HistoryModelViewHolder(
            HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoryModelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal object HistoryModelDiff : DiffUtil.ItemCallback<HistoryModel>() {
    override fun areItemsTheSame(
        oldItem: HistoryModel,
        newItem: HistoryModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: HistoryModel,
        newItem: HistoryModel
    ): Boolean {
        return oldItem.place == newItem.place && oldItem.visitTime == newItem.visitTime
    }

}


internal class HistoryModelViewHolder(
    val binding: HistoryItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        data: HistoryModel
    ) {
        binding.apply {
            binding.model = data
        }

    }
}

