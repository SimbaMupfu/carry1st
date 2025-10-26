package inc.sims.hustles.carry1st.order.data.repository

import inc.sims.hustles.carry1st.order.data.local.OrderEntity
import inc.sims.hustles.carry1st.product.model.Product
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun fetchOrders(): Flow<List<OrderEntity>>
    suspend fun addOrUpdateOrder(product: Product): Long
}