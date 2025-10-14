package fi.gibanator.subbearandroid.data

import kotlinx.coroutines.flow.Flow

class SubscriptionRepo(private val dao: SubscriptionDao) {
    fun getAllItemsStream(): Flow<List<Subscription>> = dao.observeAll()
    suspend fun add(sub: Subscription) = dao.insert(sub)
}