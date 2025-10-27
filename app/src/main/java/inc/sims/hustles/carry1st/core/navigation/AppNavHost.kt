package inc.sims.hustles.carry1st.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.sims.hustles.carry1st.order.presentation.OrderSummaryUI
import inc.sims.hustles.carry1st.order.presentation.OrdersViewModel
import inc.sims.hustles.carry1st.product.presentation.ProductDetail
import inc.sims.hustles.carry1st.product.presentation.ProductListUI
import inc.sims.hustles.carry1st.product.presentation.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavHost(navHostController: NavHostController,
               modifier: Modifier = Modifier
) {
    val productViewModel: ProductViewModel = koinViewModel()
    val orderViewModel: OrdersViewModel = koinViewModel()

    NavHost(navHostController,
        startDestination = Screen.ProductList.route,
        modifier = modifier
    ){
        composable(Screen.ProductList.route){ ProductListUI(navHostController, productViewModel) }
        composable(Screen.ProductDetail.route){ ProductDetail(navHostController, productViewModel, orderViewModel) }
        composable(Screen.OrderSummary.route) { OrderSummaryUI(orderViewModel) }
    }
}