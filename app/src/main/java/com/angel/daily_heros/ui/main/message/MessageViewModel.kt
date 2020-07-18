package com.angel.daily_heros.ui.main.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class MessageViewModel @Inject constructor() : ViewModel() {
    private val _ownerMessageModels: MutableLiveData<List<OwnerMessageModel>> = MutableLiveData(
        listOf(
            OwnerMessageModel(
                "조한진",
                "김밥천국평양점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "장도윤",
                "김밥천국부산점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "추지효",
                "김밥천국어딘가",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "이가연",
                "김밥천국안산점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ), OwnerMessageModel(
                "권경민",
                "김밥천국스프링점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            )
            , OwnerMessageModel(
                "김아무개",
                "버거킹",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "홍길동",
                "크로스핏",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "이우진",
                "우리집",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ), OwnerMessageModel(
                "현우진",
                "천안농협",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            )
        )
    )
    val ownerMessageModels: LiveData<List<OwnerMessageModel>> = _ownerMessageModels

}

data class OwnerMessageModel(
    val name: String,
    val place: String,
    val msg: String
)