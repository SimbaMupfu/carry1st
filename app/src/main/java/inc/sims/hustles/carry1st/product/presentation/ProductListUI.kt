package inc.sims.hustles.carry1st.product.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import inc.sims.hustles.carry1st.R
//import coil3.compose.AsyncImage
import inc.sims.hustles.carry1st.product.model.Product
import inc.sims.hustles.carry1st.product.utils.NetworkState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListUI(
    viewModel: ProductViewModel = koinViewModel()
) {
    val networkState by viewModel.products.collectAsState()

    when(networkState){
        is NetworkState.Loading -> {
            Box(
              modifier = Modifier
                  .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is NetworkState.Success -> {
            val productData = (networkState as NetworkState.Success).data
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                LazyColumn {
                    items(productData){ productItem ->
                        if(productItem.productStatus == "ACTIVE" && productItem.quantity > 0){
                            ProductListView(productItem)
                        }
                    }
                }
            }
        }
        is NetworkState.Error -> {
            val errorMessage = (networkState as NetworkState.Error).message
            Log.d("Product", "Error $errorMessage")
        }
    }
}

@Composable
fun ProductListView(product: Product){
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp)
    ){
        AsyncImage(
            model = product.imageURL, //painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxWidth(0.25f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(product.name)
            Text("${product.currencyCode}${product.price}")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProductListUI(){
//    val product = Product(
//        productId = 4,
//        name = "1250 Credits",
//        description = "1250 Credits product bundle.",
//        price = 5,
//        currencyCode = "USD",
//        currencySymbol = "$",
//        quantity = 1250,
//        imageURL = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/13daef82-d896-4b29-90e9-30cbcb517e24.png",
//        productStatus = "ACTIVE"
//    )
//    ProductListView(product)
//}