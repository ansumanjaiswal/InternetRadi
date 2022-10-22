package app.android.internetradio.mappers

interface Mapper<L, T> {

    suspend fun map(param: L): T
}