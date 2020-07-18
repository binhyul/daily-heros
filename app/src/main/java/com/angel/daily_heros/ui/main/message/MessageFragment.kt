package com.angel.daily_heros.ui.main.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.angel.daily_heros.databinding.MessageFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.util.activityViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MessageFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listener: MainTabsActionListener

    private lateinit var messageViewModel: MessageViewModel
    private lateinit var binding: MessageFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        messageViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            MessageFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = messageViewModel
            }


        return binding.root
    }


}


@BindingAdapter("owner_items")
fun setOwnerItems(view: RecyclerView, items: List<OwnerMessageModel>?) {

    var ownerMsgAdapter = (view.adapter as? OwnerMsgAdapter)

    if (ownerMsgAdapter == null) {
        ownerMsgAdapter = OwnerMsgAdapter()
        view.adapter = ownerMsgAdapter
    }

    ownerMsgAdapter.submitList(items.orEmpty())
}
