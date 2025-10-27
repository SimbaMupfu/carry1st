package inc.sims.hustles.carry1st.product.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import inc.sims.hustles.carry1st.core.navigation.Screen
import inc.sims.hustles.carry1st.order.presentation.OrdersViewModel

@Composable
fun ProductDetail(navController: NavHostController,
                  viewModel: ProductViewModel,
                  ordersViewModel: OrdersViewModel){
    val product by viewModel.selectedProduct.collectAsState()
    product?.let { product ->
        Column(modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = product.imageURL,
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier
                .height(8.dp)
            )

            Text(
                "Name: ${product.name}",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Text("Price: ${product.currencyCode}${product.price}",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Description:",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center)
            Text(product.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                viewModel.clearProductSelection()
                    ordersViewModel.saveOrder(product)
                    navController.navigate(Screen.OrderSummary.route)
            }) {
                Text("Buy")
            }
        }
    }
}