package app.android.internetradio.data.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChannelViewData(
    val title: String,
    val description: String,
    val iconUrl: String,
    val largeImageUrl: String,
    val dj: String,
    val djMail: String,
    val listeners: String,
    val genre: String
): Parcelable