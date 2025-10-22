package inc.sims.hustles.carry1st.product.repository

import inc.sims.hustles.carry1st.product.model.Product
import inc.sims.hustles.carry1st.product.utils.NetworkState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProductData(): Flow<NetworkState<List<Product>>>
}