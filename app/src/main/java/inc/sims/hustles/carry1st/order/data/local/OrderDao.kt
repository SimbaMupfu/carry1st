package inc.sims.hustles.carry1st.order.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import inc.sims.hustles.carry1st.order.data.local.AppDatabase.Companion.TABLE_ORDERS
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Query("SELECT * FROM $TABLE_ORDERS WHERE productId = :productId LIMIT 1")
    suspend fun getOrderByProductId(productId: Int): OrderEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Update
    suspend fun updateOrder(order: OrderEntity)

    @Query("SELECT * FROM $TABLE_ORDERS ORDER BY id DESC")
    fun getOrders(): Flow<List<OrderEntity>>

    @Query("DELETE FROM $TABLE_ORDERS WHERE id = :id")
    suspend fun deleteOrderItem(id: Long)

    @Query("SELECT SUM(price * quantityOrdered) FROM $TABLE_ORDERS")
    fun calculateOrderTotal(): Flow<Double?>
}