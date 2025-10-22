package inc.sims.hustles.carry1st

import android.app.Application
import inc.sims.hustles.carry1st.product.di.productsModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                productsModule
            )
        }
    }
}