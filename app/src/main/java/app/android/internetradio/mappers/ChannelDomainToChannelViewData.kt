package app.android.internetradio.mappers

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.data.view.ChannelViewData
import javax.inject.Inject

class ChannelDomainToChannelViewData @Inject constructor(): Mapper<Channel, ChannelViewData> {

    override suspend fun map(param: Channel): ChannelViewData {
        return ChannelViewData(
            title = param.title,
            description = param.description,
            iconUrl = param.image,
            largeImageUrl = param.largeimage ?: param.xlimage ?: param.image,
            dj = param.dj,
            djMail = param.djmail,
            listeners = param.listeners,
            genre = param.genre
        )
    }
}