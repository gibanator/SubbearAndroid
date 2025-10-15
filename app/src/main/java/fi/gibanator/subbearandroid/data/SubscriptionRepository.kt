package fi.gibanator.subbearandroid.data

import kotlinx.coroutines.flow.Flow

interface SubscriptionRepository {
    fun getAllItemsStream(): Flow<List<Subscription>>
    suspend fun add(sub: Subscription)
}