package com.angel.daily_heros.ui.main.qr.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class VisitHistoryViewModel @Inject constructor() : ViewModel(), VisitorPageActionListener {

    private val _visitorPlaceModels: MutableLiveData<List<VisitorPlaceModel>> = MutableLiveData(
        listOf(
            VisitorPlaceModel(
                "김밥천국 회기점",
                true,
                listOf(
                    VisitorModel(
                        "AAA",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "BVB",
                        36.5f,
                        "5분전"
                    ), VisitorModel(
                        "BBB",
                        36.5f,
                        "5분전"
                    )
                )
            ),
            VisitorPlaceModel(
                "시간의향기 이문점",
                false,
                listOf(
                    VisitorModel(
                        "FFF",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "GFS",
                        36.5f,
                        "5분전"
                    ), VisitorModel(
                        "QQQ",
                        36.5f,
                        "5분전"
                    ), VisitorModel(
                        "DDD",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "EFDS",
                        36.5f,
                        "20분 전"
                    ), VisitorModel(
                        "FFFF",
                        36.5f,
                        "1시간 전"
                    )
                )
            ),
            VisitorPlaceModel(
                "육쌈냉면 외대점",
                false,
                listOf(
                    VisitorModel(
                        "DDD",
                        36.5f,
                        "5분전"
                    ),
                    VisitorModel(
                        "EFDS",
                        36.5f,
                        "20분 전"
                    ), VisitorModel(
                        "FFFF",
                        36.5f,
                        "1시간 전"
                    )
                )
            )
        )
    )
    val visitorPlaceModels: LiveData<List<VisitorPlaceModel>> = _visitorPlaceModels


    val visitorModels: LiveData<List<VisitorModel>> =
        Transformations.map(visitorPlaceModels) { placeModel ->
            placeModel.find { it.checked }?.visitors
        }

    override fun onSelectPlaceTag(index: Int) {
        _visitorPlaceModels.value =
            visitorPlaceModels.value?.mapIndexed { position, visitorPlaceModel ->
                VisitorPlaceModel(
                    visitorPlaceModel.place,
                    index == position,
                    visitorPlaceModel.visitors
                )
            }
    }


}

interface VisitorPageActionListener {
    fun onSelectPlaceTag(index: Int)
}


data class VisitorPlaceModel(
    val place: String,
    val checked: Boolean,
    val visitors: List<VisitorModel>
)


data class VisitorModel(
    val name: String,
    val temperature: Float,
    val checkInTime: String
)