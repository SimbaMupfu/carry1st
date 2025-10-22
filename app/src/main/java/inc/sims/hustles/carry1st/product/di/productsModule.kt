package inc.sims.hustles.carry1st.product.di

import inc.sims.hustles.carry1st.constants.Constants
import inc.sims.hustles.carry1st.product.api.ProductApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val productsModule = module {
    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ProductApi::class.java)
    }
}