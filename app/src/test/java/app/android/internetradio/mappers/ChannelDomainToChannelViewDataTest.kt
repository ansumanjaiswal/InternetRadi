package app.android.internetradio.mappers

import app.android.internetradio.data.domain.Channel
import app.android.internetradio.data.view.ChannelViewData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ChannelDomainToChannelViewDataTest {

    lateinit var sut: ChannelDomainToChannelViewData

    @Before
    fun setup() {
        sut = ChannelDomainToChannelViewData()
    }

    @Test
    fun `When map method is called, correct mapped data is returned`() {
        //Given
        val mockChannel = mockk<Channel>()
        coEvery { mockChannel.title } returns "TITLE"
        every { mockChannel.description } returns "DESCRIPTION"
        every { mockChannel.image } returns "image"
        every { mockChannel.dj } returns "DJ"
        every { mockChannel.djmail } returns "DJMAIL"
        every { mockChannel.listeners } returns "LISTENERS"
        every { mockChannel.genre } returns "GENRE"
        every { mockChannel.largeimage } returns "LARGEIMAGE"
        every { mockChannel.xlimage } returns "XLARGEIMAGE"

        var result: ChannelViewData? = null
        //When
        runBlocking {
            result = sut.map(mockChannel)
        }

        //Then
        Assert.assertEquals(result!!.title, mockChannel.title)
        Assert.assertEquals(result!!.description, mockChannel.description)
        Assert.assertEquals(result!!.iconUrl, mockChannel.image)
        Assert.assertEquals(result!!.largeImageUrl, mockChannel.largeimage)
        Assert.assertEquals(result!!.genre, mockChannel.genre)
        Assert.assertEquals(result!!.dj, mockChannel.dj)
        Assert.assertEquals(result!!.djMail, mockChannel.djmail)
    }

}