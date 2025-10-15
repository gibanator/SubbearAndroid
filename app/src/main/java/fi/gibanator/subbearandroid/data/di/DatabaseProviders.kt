package fi.gibanator.subbearandroid.data.di


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fi.gibanator.subbearandroid.data.AppDatabase
import fi.gibanator.subbearandroid.data.DateConverter
import fi.gibanator.subbearandroid.data.PeriodUnitConverter
import fi.gibanator.subbearandroid.data.SubscriptionDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseProviders {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "subscriptions_db")
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    @Singleton
    fun provideSubscriptionDao(db: AppDatabase) : SubscriptionDao = db.subscriptionDao()
}