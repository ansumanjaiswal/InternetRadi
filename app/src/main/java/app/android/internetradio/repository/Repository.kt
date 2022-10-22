package app.android.internetradio.repository

interface Repository<T> {

    suspend fun getData(query: String): T
}