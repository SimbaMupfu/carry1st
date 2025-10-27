package inc.sims.hustles.carry1st.order.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.sims.hustles.carry1st.order.data.local.OrderEntity
import inc.sims.hustles.carry1st.order.data.repository.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OrdersViewModel(
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _savingOrder = MutableStateFlow(false)
    val savingOrder = _savingOrder.asStateFlow()

    private val _orders = MutableStateFlow<List<OrderEntity>>(emptyList())
    val orders = _orders.asStateFlow()

    private val _totalAmount = MutableStateFlow(0.0)
    val totalAmount: StateFlow<Double>
        get() = _totalAmount

    init {
        fetchOrders()
        getOrderTotal()
    }

    fun saveOrder(orderEntity: OrderEntity) = viewModelScope.launch {
        _savingOrder.value = true
        try {
            orderRepository.addOrUpdateOrder(orderEntity)
        } finally {
            _savingOrder.value = false
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

    fun getOrderTotal() = viewModelScope.launch {
        _totalAmount.value = 0.0
        orderRepository.getOrderTotal().collect {
            if (it != null) {
                _totalAmount.value = it
            }
        }
    }
}