package fi.gibanator.subbearandroid.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Subscription::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    DateConverter::class, PeriodUnitConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subscriptionDao(): SubscriptionDao
}