package inc.sims.hustles.carry1st.order.data.local

import androidx.room.Database

@Database(
    entities = [OrderEntity::class.java],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase {
    abstract fun orderDao(): OrderDao

    companion object {
        const val TABLE_ORDERS = "orders_table"
    }
}