package inc.sims.hustles.carry1st.product.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import inc.sims.hustles.carry1st.product.utils.NetworkState
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListUI(
    viewModel: ProductViewModel = koinViewModel()
) {
    LaunchedEffect(true) {
        viewModel.loadProducts()

        viewModel.products.collectLatest { networkState ->
            when(networkState){
                is NetworkState.Loading -> {
                    Log.d("Product", "Loading")
                }
                is NetworkState.Success -> {
                    for(product in networkState.data){
                        Log.d("Product", product.name)
                    }
                }
                is NetworkState.Error -> {
                    Log.d("Product", "Error ${networkState.message}")
                }
            }
        }
    }
}