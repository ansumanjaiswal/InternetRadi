package app.android.internetradio.repository

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.dataSources.DataSource

class ChannelListRepository(private val dataSource: DataSource<List<Channel>>) :
    Repository<List<Channel>> {

    override suspend fun getData(query: String): List<Channel> {
        return dataSource.getData(query)
    }
}