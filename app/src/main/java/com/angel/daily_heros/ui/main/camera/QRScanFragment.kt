package com.angel.daily_heros.ui.main.camera

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.angel.daily_heros.databinding.QrCameraFragBinding
import com.angel.daily_heros.util.activityViewModelProvider
import com.angel.daily_heros.util.requestPermissionForCamera
import dagger.android.support.DaggerFragment
import me.dm7.barcodescanner.zbar.BarcodeFormat
import javax.inject.Inject

class QRScanFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: QRScanViewModel
    private lateinit var binding: QrCameraFragBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activityViewModelProvider(viewModelFactory)

        binding =
            QrCameraFragBinding.inflate(inflater, container, false).apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = viewModel
            }

        requestPermissionForCamera(requireContext()) {
            runQRScan()
        }

        return binding.root
    }


    private fun runQRScan() {

        binding.scannerView.setLaserEnabled(false)
        binding.scannerView.setSquareViewFinder(true)
        binding.scannerView.setBorderAlpha(0f)
        binding.scannerView.setMaskColor(0x00000000)
        binding.scannerView.setFormats(List<BarcodeFormat>(1) { BarcodeFormat.QRCODE })
        binding.scannerView.startCamera()

        binding.scannerView.setResultHandler { rawResult ->

            Log.d("barcodeFormat", rawResult.barcodeFormat.id.toString())
            Log.d("barcodeFormat", rawResult.barcodeFormat.name)
            Log.d("barcodeFormat", rawResult.contents)

            binding.scannerView.stopCamera()

//            viewModel.onRecognized(rawResult.contents)

            Thread.sleep(1000)
        }
    }
}