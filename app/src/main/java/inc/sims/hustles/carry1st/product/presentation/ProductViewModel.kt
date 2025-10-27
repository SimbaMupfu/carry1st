package inc.sims.hustles.carry1st.product.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.sims.hustles.carry1st.product.model.Product
import inc.sims.hustles.carry1st.product.repository.ProductRepository
import inc.sims.hustles.carry1st.product.utils.NetworkState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository): ViewModel() {

    private val _products = MutableStateFlow<NetworkState<List<Product>>>(NetworkState.Loading)
    val products: StateFlow<NetworkState<List<Product>>> = _products

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    init {
        loadProducts()
    }

    fun loadProducts() = viewModelScope.launch {
        _products.value = NetworkState.Loading
        productRepository.getProductData().collect {
            _products.value = it
        }
    }

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun clearProductSelection(){
        _selectedProduct.value = null
    }
}