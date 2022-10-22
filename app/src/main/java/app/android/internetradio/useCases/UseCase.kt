package app.android.internetradio.useCases

interface UseCase<T> {

    suspend fun getData(): T
}