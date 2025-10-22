package inc.sims.hustles.carry1st.product.di

import inc.sims.hustles.carry1st.constants.Constants
import inc.sims.hustles.carry1st.product.api.ProductApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val productsModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .build()
            .create(ProductApi::class.java)
    }
}