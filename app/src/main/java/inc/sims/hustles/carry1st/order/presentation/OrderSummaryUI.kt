package inc.sims.hustles.carry1st.order.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import inc.sims.hustles.carry1st.order.data.local.OrderEntity

@Composable
fun OrderSummaryUI(ordersViewModel: OrdersViewModel) {
    val orders by ordersViewModel.orders.collectAsState()
    val totalAmount by ordersViewModel.totalAmount.collectAsState()

    if (orders.isEmpty()) {
        Log.d("Orders", "No orders to display")
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Your Order", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(12.dp))

            LazyColumn {
                items(orders) { orderItem ->
                    OrderListView(orderItem) {
                        ordersViewModel.deleteOrderItem(orderItem)
                    }
                }
            }

            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)

            Text(
                text = "Total: ${if (orders.firstOrNull()?.currencySymbol != null) orders.first().currencySymbol else "$"}${"%.2f".format(totalAmount)}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 12.dp)
            )
        }
    }

}

@Composable
fun OrderListView(orderEntity: OrderEntity, onDelete: (OrderEntity) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = orderEntity.imageURL,
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth(0.25f)
            )

            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(orderEntity.name)
                Text("${orderEntity.currencyCode}${orderEntity.price}")
            }
            IconButton(
                modifier = Modifier.weight(0.15f),
                onClick = { onDelete(orderEntity) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete icon",
                    tint = Color.Red
                )
            }
        }
    }
}