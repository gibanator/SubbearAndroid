package fi.gibanator.subbearandroid.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface SubscriptionRepository {
    fun getAllItemsStream(): Flow<List<Subscription>>
    suspend fun add(sub: Subscription)
}

@Singleton
class SubscriptionRepositoryImpl @Inject constructor(
    private val dao: SubscriptionDao
) : SubscriptionRepository {
    override fun getAllItemsStream(): Flow<List<Subscription>> {
        TODO("Not yet implemented")
    }

    override suspend fun add(sub: Subscription) = dao.insert(sub)
}