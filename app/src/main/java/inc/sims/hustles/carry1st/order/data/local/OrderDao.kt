package inc.sims.hustles.carry1st.order.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import inc.sims.hustles.carry1st.order.data.local.AppDatabase.Companion.TABLE_ORDERS
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Upsert
    suspend fun upsert(orderEntity: OrderEntity): Long

    @Query("SELECT * FROM $TABLE_ORDERS ORDER BY id DESC")
    fun getOrders(): Flow<List<OrderEntity>>

    @Query("DELETE FROM $TABLE_ORDERS WHERE id = :id")
    suspend fun deleteOrderItem(id: Long)
}