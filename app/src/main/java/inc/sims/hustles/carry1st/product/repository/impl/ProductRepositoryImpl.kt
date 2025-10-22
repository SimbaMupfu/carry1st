package inc.sims.hustles.carry1st.product.repository.impl

import inc.sims.hustles.carry1st.product.api.ProductApi
import inc.sims.hustles.carry1st.product.model.Product
import inc.sims.hustles.carry1st.product.repository.ProductRepository
import inc.sims.hustles.carry1st.product.utils.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ProductRepositoryImpl(private val productApi: ProductApi): ProductRepository {
    override suspend fun getProductData(): Flow<NetworkState<List<Product>>> = flow{
        emit(NetworkState.Loading)
        try {
            val response = productApi.fetchProducts()
            if(response.isSuccessful && response.body() != null){
                emit(NetworkState.Success(response.body()!!))
            }else {
                emit(NetworkState.Error("Error: ${response.message()}"))
            }
        } catch (ex: HttpException){
            emit(NetworkState.Error("Error: ${ex.message}", ex.code()))
        } catch (ex: Exception){
            emit(NetworkState.Error("Error: ${ex.message}"))
        }
    }
}