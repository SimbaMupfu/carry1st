package inc.sims.hustles.carry1st.order.data.repository

import inc.sims.hustles.carry1st.order.data.local.OrderEntity
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun fetchOrders(): Flow<List<OrderEntity>>
    suspend fun addOrUpdateOrder(orderEntity: OrderEntity)
    suspend fun deleteOrderItem(orderEntity: OrderEntity)
    suspend fun getOrderTotal(): Flow<Double?>
}