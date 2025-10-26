package inc.sims.hustles.carry1st.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import inc.sims.hustles.carry1st.product.presentation.ProductDetail
import inc.sims.hustles.carry1st.product.presentation.ProductListUI

@Composable
fun AppNavHost(navHostController: NavHostController,
               modifier: Modifier = Modifier
) {
    NavHost(navHostController,
        startDestination = Screen.ProductList.route,
        modifier = modifier
    ){
        composable(Screen.ProductList.route){ ProductListUI() }
        composable(Screen.ProductDetail.route){ ProductDetail() }
    }
}