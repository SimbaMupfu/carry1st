package inc.sims.hustles.carry1st.product.repository.impl

import inc.sims.hustles.carry1st.product.api.ProductApi
import inc.sims.hustles.carry1st.product.model.Product
import inc.sims.hustles.carry1st.product.repository.ProductRepository
import inc.sims.hustles.carry1st.product.utils.NetworkState
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(private val productApi: ProductApi): ProductRepository {
    override suspend fun getProductData(): Flow<NetworkState<List<Product>>> {
        TODO("Not yet implemented")
    }
}