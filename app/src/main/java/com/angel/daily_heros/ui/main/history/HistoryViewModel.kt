package com.angel.daily_heros.ui.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HistoryViewModel @Inject constructor() : ViewModel() {

    private val _userName: MutableLiveData<String> = MutableLiveData("아무개")
    val userName: LiveData<String> = _userName

    private val _historyModels: MutableLiveData<List<HistoryModel>> = MutableLiveData(
        listOf(
            HistoryModel(
                "None",
                "조한진",
                "김밥천국평양점",
                "0일전 방문"
            ),
            HistoryModel(
                "None",
                "장도윤",
                "김밥천국부산점",
                "0일전 방문"
            ),
            HistoryModel(
                "None",
                "추지효",
                "김밥천국어딘가",
                "0일전 방문"
            ),
            HistoryModel(
                "None",
                "이가연",
                "김밥천국안산점",
                "0일전 방문"
            ), HistoryModel(
                "None",
                "권경민",
                "김밥천국스프링점",
                "0일전 방문"
            )
            , HistoryModel(
                "None",
                "김아무개",
                "버거킹",
                "0일전 방문"
            ),
            HistoryModel(
                "None",
                "홍길동",
                "크로스핏",
                "0일전 방문"
            ),
            HistoryModel(
                "None",
                "이우진",
                "우리집",
                "0일전 방문"
            ), HistoryModel(
                "None",
                "현우진",
                "천안농협",
                "0일전 방문"
            )
        )
    )
    val historyModels: LiveData<List<HistoryModel>> = _historyModels


}


data class HistoryModel(
    val profile: String,
    val name: String,
    val place: String,
    val visitTime: String
)