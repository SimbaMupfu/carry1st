package inc.sims.hustles.carry1st.order.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_ORDERS)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "productId")
    val productId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "currencyCode")
    val currencyCode: String,
    @ColumnInfo(name = "currencySymbol")
    val currencySymbol: String,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "imageLocation")
    val imageURL: String,
    @ColumnInfo(name = "status")
    val productStatus: String
)
