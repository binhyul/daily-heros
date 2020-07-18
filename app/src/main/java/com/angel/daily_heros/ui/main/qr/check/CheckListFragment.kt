package com.angel.daily_heros.ui.main.qr.check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.angel.daily_heros.databinding.CheckListFragBinding
import com.angel.daily_heros.ui.main.MainTabsActionListener
import com.angel.daily_heros.util.EventObserver
import com.angel.daily_heros.util.activityViewModelProvider
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CheckListFragment : DaggerFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var listener: MainTabsActionListener

    private lateinit var checkListViewModel: CheckListViewModel
    private lateinit var binding: CheckListFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkListViewModel = activityViewModelProvider(viewModelFactory)

        binding =
            CheckListFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = checkListViewModel
                actionListener = checkListViewModel
            }

        checkListViewModel.submit.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(CheckListFragmentDirections.actionCheckListFragmentToVisitHistoryFragment())
        })


        return binding.root
    }
}