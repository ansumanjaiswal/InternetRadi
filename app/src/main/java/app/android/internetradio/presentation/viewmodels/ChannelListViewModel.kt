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

    private val _errorMessageVisibility = MutableStateFlow(View.GONE)
    val errorMessageVisibility = _errorMessageVisibility.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    // This can be persisted in a in memory cache or in sqlite db based on the business requirement
    // For the current project and in the interest of time keeping it local to ViewModel, to avoid reaching network every time
    private var listData: List<ChannelViewData> = emptyList()

    private fun fetchChannelList() {
        viewModelScope.launch(Dispatchers.IO) {
            _progressBarVisibility.emit(View.VISIBLE)
            _errorMessageVisibility.emit(View.GONE)
            listData = useCase.getData()

            withContext(Dispatchers.Main) {
                if(listData.isEmpty()) {
                    val errorMessage = "Error in retrieving data. Please try again in sometime"
                    _errorMessageVisibility.emit(View.VISIBLE)
                    _errorMessage.emit(errorMessage)
                }
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
