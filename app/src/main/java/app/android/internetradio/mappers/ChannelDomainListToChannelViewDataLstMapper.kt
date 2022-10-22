package app.android.internetradio.mappers

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.data.view.ChannelViewData
import javax.inject.Inject

class ChannelDomainListToChannelViewDataLstMapper @Inject constructor(private val mapper: ChannelDomainToChannelViewData) :
    Mapper<List<Channel>, List<ChannelViewData>> {

    override suspend fun map(param: List<Channel>): List<ChannelViewData> {
        return param.map {
            mapper.map(it)
        }
    }
}