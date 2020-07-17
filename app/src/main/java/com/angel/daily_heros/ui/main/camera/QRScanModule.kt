package com.angel.daily_heros.ui.main.camera

import androidx.lifecycle.ViewModel
import com.angel.daily_heros.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


/**
 * Module where classes needed to create the [MainRegisterGuideFragment] are defined.
 */
@Module
@Suppress("UNUSED")
internal abstract class QRScanModule {

    /**
     * Generates an [AndroidInjector] for the [MainRegisterGuideFragment] as a Dagger subcomponent of the
     * [MainActivityModule].
     */
    @ContributesAndroidInjector
    internal abstract fun contributeQRScanFragment(): QRScanFragment

    /**
     * The ViewModels are created by Dagger in a map. Via the @ViewModelKey, we define that we
     * want to get a [MainTabsViewModel] class.
     */
    @Binds
    @IntoMap
    @ViewModelKey(QRScanViewModel::class)
    abstract fun bindQRScanViewModel(viewModel: QRScanViewModel): ViewModel

}
