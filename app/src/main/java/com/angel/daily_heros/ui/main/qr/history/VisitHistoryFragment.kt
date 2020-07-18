package com.angel.daily_heros.ui.main.qr.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.VisitorHistoryFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.util.activityViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class VisitHistoryFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listener: MainTabsActionListener

    private lateinit var visitViewModel: VisitHistoryViewModel
    private lateinit var binding: VisitorHistoryFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        visitViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            VisitorHistoryFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = visitViewModel
                actionListener = visitViewModel
            }



        return binding.root
    }
}


@BindingAdapter("visitor_place_tag_items", "action_listener")
fun setVisitorPlaceTagItems(
    view: RecyclerView,
    items: List<VisitorPlaceModel>?,
    actionListener: VisitorPageActionListener
) {

    var visitorPlaceTagListAdapter = (view.adapter as? VisitorPlaceTagListAdapter)
    view.itemAnimator = null
    if (visitorPlaceTagListAdapter == null) {
        visitorPlaceTagListAdapter = VisitorPlaceTagListAdapter(actionListener)
        view.adapter = visitorPlaceTagListAdapter
    }

    visitorPlaceTagListAdapter.submitList(items.orEmpty())
}


@BindingAdapter("visitor_history_items")
fun setVisitorHistoryItems(view: RecyclerView, items: List<VisitorModel>?) {

    var visitorHistoryListAdapter = (view.adapter as? VisitorHistoryListAdapter)
    view.itemAnimator = null
    if (visitorHistoryListAdapter == null) {
        visitorHistoryListAdapter = VisitorHistoryListAdapter()
        view.adapter = visitorHistoryListAdapter
    }

    visitorHistoryListAdapter.submitList(items.orEmpty())
}