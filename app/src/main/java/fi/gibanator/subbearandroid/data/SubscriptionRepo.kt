package fi.gibanator.subbearandroid.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscriptionRepo @Inject constructor(private val dao: SubscriptionDao) {
    fun getAllItemsStream(): Flow<List<Subscription>> = dao.observeAll()
    suspend fun add(sub: Subscription) = dao.insert(sub)
}