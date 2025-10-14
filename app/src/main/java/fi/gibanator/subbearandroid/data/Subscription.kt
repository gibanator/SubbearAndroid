package fi.gibanator.subbearandroid.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "subscriptions")
data class Subscription(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val logoUri: String?,

    val periodUnit: PeriodUnit,
    val unitCount: Int = 0,

    val startDate: LocalDate,
    val nextCharge: LocalDate,
)

enum class PeriodUnit { DAY, WEEK, MONTH, YEAR }