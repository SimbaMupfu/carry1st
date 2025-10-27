package inc.sims.hustles.carry1st.order.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.sims.hustles.carry1st.order.data.local.OrderEntity
import inc.sims.hustles.carry1st.order.data.repository.OrderRepository
import inc.sims.hustles.carry1st.product.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val orderRepository: OrderRepository
): ViewModel() {
    private val _savingOrder = MutableStateFlow(false)
    val savingOrder = _savingOrder.asStateFlow()

    private val _orders = MutableStateFlow<List<OrderEntity>>(emptyList())
    val orders = _orders.asStateFlow()

    init {
        fetchOrders()
    }

    fun saveOrder(product: Product) = viewModelScope.launch {
        _savingOrder.value = true
        try {
            orderRepository.addOrUpdateOrder(product)
        }finally {
            _savingOrder.value =  false
        }
    }

    fun fetchOrders() = viewModelScope.launch {
        _orders.value = emptyList()
        orderRepository.fetchOrders().collect {
            _orders.value = it
        }
    }

    fun deleteOrderItem(orderEntity: OrderEntity) = viewModelScope.launch {
        orderRepository.deleteOrderItem(orderEntity)
    }
}