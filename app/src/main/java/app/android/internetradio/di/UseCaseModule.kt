package app.android.internetradio.di

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.data.view.ChannelViewData
import app.android.internetradio.mappers.ChannelDomainListToChannelViewDataLstMapper
import app.android.internetradio.repository.Repository
import app.android.internetradio.useCases.ChannelListUseCase
import app.android.internetradio.useCases.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSchoolListUseCase(
        repository: Repository<List<Channel>>,
        mapper: ChannelDomainListToChannelViewDataLstMapper
    ): UseCase<List<ChannelViewData>> {
        return ChannelListUseCase(
            repository = repository,
            mapper = mapper
        )
    }
}