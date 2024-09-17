package data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.database.dao.VacancyDao
import com.example.core.data.database.entity.VacancyEntity

@Database(entities = [VacancyEntity::class], version = 1)
abstract class VacancyDataBase : RoomDatabase() {
    abstract fun getDao(): VacancyDao
}
