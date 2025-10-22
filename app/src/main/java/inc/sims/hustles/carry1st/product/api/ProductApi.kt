package inc.sims.hustles.carry1st.product.api

import inc.sims.hustles.carry1st.product.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("/productBundles")
    suspend fun fetchProducts(): Response<List<Product>>
}