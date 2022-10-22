package app.android.internetradio.di

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.dataSources.DataSource
import app.android.internetradio.repository.ChannelListRepository
import app.android.internetradio.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideChannelListRepository(dataSource: DataSource<List<Channel>>):
            Repository<List<Channel>> {
        return ChannelListRepository(dataSource = dataSource)
    }
}