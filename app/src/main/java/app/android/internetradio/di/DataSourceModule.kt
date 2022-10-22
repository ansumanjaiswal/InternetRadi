package app.android.internetradio.di

import app.android.internetradio.api.ChannelListService
import app.android.internetradio.data.domain.Channel
import app.android.internetradio.dataSources.ChannelListDataSource
import app.android.internetradio.dataSources.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideChannelListDataSource(service: ChannelListService): DataSource<List<Channel>> {
        return ChannelListDataSource(service)
    }
}