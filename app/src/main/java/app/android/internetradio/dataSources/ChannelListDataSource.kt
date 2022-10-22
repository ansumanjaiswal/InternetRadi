package app.android.internetradio.dataSources

import app.android.internetradio.api.ChannelListService
import app.android.internetradio.data.domain.Channel

class ChannelListDataSource(private val service: ChannelListService): DataSource<List<Channel>> {

    override suspend fun getData(query: String): List<Channel> {
        val data = service.getChannelList(query)
        return if (data.isSuccessful && data.body() != null) {
            data.body()!!.channels
        } else {
            emptyList()
        }
    }
}