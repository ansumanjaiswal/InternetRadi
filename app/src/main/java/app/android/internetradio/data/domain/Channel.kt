package app.android.internetradio.data.domain

data class Channel(
    val description: String,
    val dj: String,
    val djmail: String,
    val genre: String,
    val id: String,
    val image: String,
    val largeimage: String?,
    val lastPlaying: String,
    val listeners: String,
    val playlists: List<Playlists>,
    val title: String,
    val twitter: String,
    val updated: String,
    val xlimage: String?
)

data class Playlists(
    val format: String,
    val quality: String,
    val url: String
)