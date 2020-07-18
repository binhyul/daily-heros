package com.angel.daily_heros.ui.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.angel.daily_heros.R
import com.angel.daily_heros.databinding.MainFragBinding
import com.angel.daily_heros.ui.main.history.HistoryFragment
import com.angel.daily_heros.ui.main.message.MessageFragment
import com.angel.daily_heros.util.activityViewModelProvider
import com.angel.daily_heros.util.requestPermissionForCamera
import dagger.android.support.DaggerFragment
import me.dm7.barcodescanner.zbar.BarcodeFormat
import javax.inject.Inject

class MainTabsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainModel: MainTabsViewModel
    private lateinit var binding: MainFragBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (::mainModel.isInitialized && ::binding.isInitialized) {
            mainModel.createTabModels()
            inflateTabContentFragments()
            changeContent(mainModel.updateModel())
            binding.apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = mainModel
                actionListener = mainModel
            }


        } else {
            mainModel = activityViewModelProvider(viewModelFactory)
            mainModel.createTabModels()
            binding = MainFragBinding.inflate(inflater, container, false)
                .apply {
                    lifecycleOwner = viewLifecycleOwner
                    viewModel = mainModel
                    actionListener = mainModel
                }

            inflateTabContentFragments()


        }

        mainModel.selectTabIndex.observe(viewLifecycleOwner, Observer {
            val to = it
            changeContent(to)
        })

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun runQRScan() {
        binding.clQrPage.visibility = View.VISIBLE
        binding.scannerView.setLaserEnabled(false)
        binding.scannerView.setSquareViewFinder(true)
        binding.scannerView.setBorderAlpha(1f)
        binding.scannerView.setMaskColor(0x00000000)
        binding.scannerView.setBorderStrokeWidth(resources.getDimensionPixelOffset(R.dimen.qr_border_width))
        binding.scannerView.setBorderColor(Color.parseColor("#727272"))
        binding.scannerView.setFormats(List<BarcodeFormat>(1) { BarcodeFormat.QRCODE })
        binding.scannerView.startCamera()

        binding.scannerView.setResultHandler { rawResult ->

            Log.d("barcodeFormat", rawResult.barcodeFormat.id.toString())
            Log.d("barcodeFormat", rawResult.barcodeFormat.name)
            Log.d("barcodeFormat", rawResult.contents)

            binding.scannerView.stopCamera()

            findNavController().navigate(MainTabsFragmentDirections.actionMainTabsFragmentToCheckListFragment())

//            viewModel.onRecognized(rawResult.contents)

            Thread.sleep(1000)
        }
    }


    private fun setFirstStatus(model: List<TabModel>) {
        model.forEach {
            binding.root.findViewById<FrameLayout>(it.id).visibility = View.GONE
        }
    }

    private fun showFragment(model: List<TabModel>, index: Int) {
        if (index != 0) {
            if (binding.clQrPage.visibility == View.VISIBLE) {
                stopQRScanner()
            }
            binding.root.findViewById<FrameLayout>(model[index].id).apply {
                visibility = View.VISIBLE
            }
        } else {
            requestPermissionForCamera(requireContext()) {
                runQRScan()
            }
        }
    }


    private fun changeContent(to: Int) {
        mainModel.tabsModel.value?.let {
            setFirstStatus(it)
            showFragment(it, to)
        }
    }

    private fun stopQRScanner() {
        binding.clQrPage.visibility = View.GONE
        binding.scannerView.stopCamera()
    }


    private fun inflateTabContentFragments() {
        mainModel.tabsModel.value?.forEachIndexed { index, tabModel ->
            if (index != 0) {
                val fragment = createContentFragment(index)
                fragmentManager?.beginTransaction()
                    ?.apply {
                        replace(tabModel.id, fragment)
                        commit()
                    }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        changeContent(mainModel.updateModel())
    }

    override fun onResume() {
        super.onResume()
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun createContentFragment(position: Int): Fragment {
        return when (mainModel.tabsModel.value!![position].fragment) {
            TabContentFragment.HISTORY -> HistoryFragment().apply {
                listener = mainModel
            }
            TabContentFragment.NOTICE -> MessageFragment().apply {
                listener = mainModel
            }
            else -> HistoryFragment().apply {
                listener = mainModel
            }

        }
    }

}
