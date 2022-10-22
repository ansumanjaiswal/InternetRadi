package app.android.internetradio.dataSources

interface DataSource<T> {

    suspend fun getData(query: String): T
}