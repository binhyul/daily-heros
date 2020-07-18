package com.angel.daily_heros.ui.main.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.angel.daily_heros.ui.main.history.HistoryModel
import com.angel.daily_heros.ui.main.history.HistoryPageMode
import com.angel.daily_heros.util.Event
import javax.inject.Inject


class MessageViewModel @Inject constructor() : ViewModel(), MessageActionListener,
    MessagePageActionListener {
    private val _ownerMessageModels: MutableLiveData<List<OwnerMessageModel>> = MutableLiveData(
        listOf(
            OwnerMessageModel(
                "조한진",
                "None",
                "김밥천국평양점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "장도윤",
                "None",
                "김밥천국부산점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "추지효",
                "None",
                "김밥천국어딘가",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "이가연", "None",
                "김밥천국안산점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ), OwnerMessageModel(
                "권경민", "None",
                "김밥천국스프링점",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            )
            , OwnerMessageModel(
                "김아무개", "None",
                "버거킹",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "홍길동", "None",
                "크로스핏",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ),
            OwnerMessageModel(
                "이우진", "None",
                "우리집",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            ), OwnerMessageModel(
                "현우진", "None",
                "천안농협",
                "고단한 하루지만 고객님의 즐거움이 되기 위해 저희는 오늘도 정성껏 만들고 있습니다~ 코로나 방지를 위해 방역도 완료 되었습니다."
            )
        )
    )
    val ownerMessageModels: LiveData<List<OwnerMessageModel>> = _ownerMessageModels

    private val _mode: MutableLiveData<MessagePageMode> = MutableLiveData(MessagePageMode.LIST)
    val mode: LiveData<MessagePageMode> = _mode

    private val _boardItem: MutableLiveData<OwnerMessageModel> = MutableLiveData(
        OwnerMessageModel(
            "",
            "",
            "",
            ""
        )
    )
    val boardItem: LiveData<OwnerMessageModel> = _boardItem

    fun modeBack() {
        _mode.value = when (mode.value) {
            MessagePageMode.VIEW -> MessagePageMode.LIST
            MessagePageMode.WHITE_BOARD -> MessagePageMode.VIEW
            else -> MessagePageMode.LIST
        }
    }

    companion object {

        val _back: MutableLiveData<Event<Unit>> = MutableLiveData()
        val back: LiveData<Event<Unit>> = _back

    }

    override fun onClickMessage(position: Int) {
        _boardItem.value = ownerMessageModels.value?.get(position)
        _mode.value = MessagePageMode.VIEW
    }

    override fun onWriteBoard() {
        _mode.value = MessagePageMode.WHITE_BOARD
    }

    override fun onClickSubmit() {

    }

}

data class OwnerMessageModel(
    val name: String,
    val profile: String,
    val place: String,
    val msg: String
)

enum class MessagePageMode {
    LIST, VIEW, WHITE_BOARD
}

interface MessagePageActionListener {
    fun onClickSubmit()
    fun onWriteBoard()
}