package app.android.internetradio.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.useCases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChannelListViewModel @Inject constructor(val useCase: UseCase<List<ChannelViewData>>) :
    ViewModel() {

    private val _channelListFlow = MutableSharedFlow<List<ChannelViewData>>()
    val channelListFlow = _channelListFlow.asSharedFlow()

    fun fetchChannelList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.getData()
            _channelListFlow.emit(response)

           /* withContext(Dispatchers.Main) {
                _channelListFlow.emit(response)
            }*/
        }
    }
}