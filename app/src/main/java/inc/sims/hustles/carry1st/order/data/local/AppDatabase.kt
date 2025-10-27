package inc.sims.hustles.carry1st.order.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [OrderEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        const val TABLE_ORDERS = "orders_table"
    }
}