package app.android.internetradio.presentation.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.useCases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChannelListViewModel @Inject constructor(val useCase: UseCase<List<ChannelViewData>>) :
    ViewModel() {

    private val _channelListFlow = MutableStateFlow(emptyList<ChannelViewData>())
    val channelListFlow = _channelListFlow.asStateFlow()

    private val _progressBarVisibility = MutableStateFlow(View.VISIBLE)
    val progressBarVisibility = _progressBarVisibility.asStateFlow()

    private var listData: List<ChannelViewData> = emptyList()

    private fun fetchChannelList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.emit(View.VISIBLE)
            listData = useCase.getData()

            withContext(Dispatchers.Main) {
                _progressBarVisibility.emit(View.GONE)
                _channelListFlow.emit(listData)
            }
        }
    }

    fun getChannelListData() : List<ChannelViewData> {
        if(listData.isEmpty()) {
            fetchChannelList()
        }
        return listData
    }

    fun filterByDJ(dj: String) : List<ChannelViewData> {
        return listData.filter {
            it.dj == dj
        }
    }
}
