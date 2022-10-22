package app.android.internetradio.api

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.data.domain.ChannelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ChannelListService {

    @GET()
    suspend fun getChannelList(@Url apiEndpoint: String): Response<ChannelResponse>
}