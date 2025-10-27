package inc.sims.hustles.carry1st.order.data.repository.impl

import inc.sims.hustles.carry1st.order.data.local.OrderDao
import inc.sims.hustles.carry1st.order.data.local.OrderEntity
import inc.sims.hustles.carry1st.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(private val orderDao: OrderDao): OrderRepository {
    override fun fetchOrders() = orderDao.getOrders()

    override suspend fun addOrUpdateOrder(orderEntity: OrderEntity) {
        val existingOrder = orderDao.getOrderByProductId(orderEntity.productId)
        if (existingOrder != null) {
            val updatedOrder = existingOrder.copy(quantityOrdered = existingOrder.quantityOrdered + 1)
            orderDao.updateOrder(updatedOrder)
        } else {
            orderDao.insertOrder(orderEntity.copy(quantityOrdered = 1))
        }
    }

    override suspend fun deleteOrderItem(orderEntity: OrderEntity) {
        orderDao.deleteOrderItem(orderEntity.id)
    }

    override suspend fun getOrderTotal(): Flow<Double?> = orderDao.calculateOrderTotal()
}