package com.angel.daily_heros.ui.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.HistoryFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.util.activityViewModelProvider
import com.bumptech.glide.Glide
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HistoryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listener: MainTabsActionListener

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var binding: HistoryFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            HistoryFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = historyViewModel
            }


        return binding.root
    }


}

@BindingAdapter("history_items")
fun setHistoryItems(view: RecyclerView, items: List<HistoryModel>?) {

    var historyAdapter = (view.adapter as? HistoryAdapter)

    if (historyAdapter == null) {
        historyAdapter = HistoryAdapter()
        view.adapter = historyAdapter
    }

    historyAdapter.submitList(items.orEmpty())
}

@BindingAdapter("android:glide_src")
fun setImage(view: ImageView, url: String) {
    Glide.with(view).load(url).into(view)
}
