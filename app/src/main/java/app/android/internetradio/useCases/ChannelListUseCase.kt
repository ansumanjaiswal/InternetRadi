package app.android.internetradio.useCases

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.mappers.ChannelDomainListToChannelViewDataLstMapper
import app.android.internetradio.mappers.Mapper
import app.android.internetradio.repository.Repository

class ChannelListUseCase(
    private val repository: Repository<List<Channel>>,
    private val mapper: ChannelDomainListToChannelViewDataLstMapper
) : UseCase<List<ChannelViewData>> {

    override suspend fun getData(): List<ChannelViewData> {
        return mapper.map(repository.getData(ENDPOINT))
    }

    companion object{
        const val ENDPOINT = "channels.json"
    }
}