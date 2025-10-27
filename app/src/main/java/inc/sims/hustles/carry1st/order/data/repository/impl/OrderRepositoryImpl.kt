package inc.sims.hustles.carry1st.order.data.repository.impl

import inc.sims.hustles.carry1st.order.data.local.OrderDao
import inc.sims.hustles.carry1st.order.data.local.OrderEntity
import inc.sims.hustles.carry1st.order.data.repository.OrderRepository
import inc.sims.hustles.carry1st.product.model.Product

class OrderRepositoryImpl(private val orderDao: OrderDao): OrderRepository {
    override fun fetchOrders() = orderDao.getOrders()

    override suspend fun addOrUpdateOrder(product: Product) =
        orderDao.upsert(OrderEntity(
            productId = product.productId,
            name = product.name,
            description = product.description,
            price = product.price,
            currencyCode = product.currencyCode,
            currencySymbol = product.currencySymbol,
            quantity = product.quantity,
            imageURL = product.imageURL,
            productStatus = product.productStatus
        ))

    override suspend fun deleteOrderItem(orderEntity: OrderEntity) {
        orderDao.deleteOrderItem(orderEntity.id)
    }
}