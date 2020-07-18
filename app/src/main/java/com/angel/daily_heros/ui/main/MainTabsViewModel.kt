package com.angel.daily_heros.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angel.daily_heros.R
import javax.inject.Inject

class MainTabsViewModel @Inject constructor(
) : ViewModel(), MainTabsActionListener {


    private val _tabsModel = MutableLiveData<List<TabModel>>()
    val tabsModel: LiveData<List<TabModel>> = _tabsModel

    private val _selectTabIndex = MutableLiveData<Int>(0)
    val selectTabIndex: LiveData<Int> = _selectTabIndex

    override fun onClickTab(to: Int) {
        _selectTabIndex.value.let {
            _tabsModel.value = _tabsModel.value!!.mapIndexed { id, item ->
                item.selected = id == to
                item
            }
            _selectTabIndex.value = to
        }
    }


    fun updateModel(): Int {
        //Refresh event for animation.
        val index = tabsModel.value?.indexOf(tabsModel.value?.find { it.selected })
        _selectTabIndex.value = index
        return index ?: 0
    }

    fun createTabModels() {
        _tabsModel.value = listOf(
            TabModel(
                true,
                TabContentFragment.QRSCANNER,
                R.id.fl_main_act_qr
            ),

            TabModel(
                false,
                TabContentFragment.HISTORY,
                R.id.fl_main_act_history
            ),
            TabModel(
                false,
                TabContentFragment.NOTICE,
                R.id.fl_main_act_notice
            ),
            TabModel(
                false,
                TabContentFragment.NOTICE,
                R.id.fl_main_act_mypage
            )
        )
    }

    companion object {

    }

}

interface MainTabsActionListener {
    fun onClickTab(to: Int)
}

data class TabModel(
    var selected: Boolean,
    val fragment: TabContentFragment,
    val id: Int
)


enum class TabContentFragment {
    QRSCANNER, HISTORY, NOTICE, MYPAGE
}
