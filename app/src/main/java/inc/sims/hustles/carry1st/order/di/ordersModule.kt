package inc.sims.hustles.carry1st.order.di

import androidx.room.Room
import inc.sims.hustles.carry1st.order.data.local.AppDatabase
import inc.sims.hustles.carry1st.order.data.repository.OrderRepository
import inc.sims.hustles.carry1st.order.data.repository.impl.OrderRepositoryImpl
import inc.sims.hustles.carry1st.order.presentation.OrdersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ordersModule = module {
    single {
        Room.databaseBuilder<AppDatabase>(
            get(),
            "carryfirst_db"
        ).build()
    }

    single { get<AppDatabase>().orderDao() }

    single<OrderRepository> { OrderRepositoryImpl(get()) }

    viewModel { OrdersViewModel(get()) }
}