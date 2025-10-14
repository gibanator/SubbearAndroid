package fi.gibanator.subbearandroid.data

import androidx.room.TypeConverter
import java.time.LocalDate

class DateConverter{
    @TypeConverter
    fun fromLocalDate(d: LocalDate?): String? = d?.toString()
    @TypeConverter
    fun toLocalDate(s: String?): LocalDate? = s?.let(LocalDate::parse)
}

class PeriodUnitConverter{
    @TypeConverter
    fun fromUnit(u: PeriodUnit?): String? = u?.name
    @TypeConverter
    fun toUnit(s: String?): PeriodUnit? = s?.let { PeriodUnit.valueOf(it) }
}