package inc.sims.hustles.carry1st.product.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val productId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("currencyCode")
    val currencyCode: String,
    @SerializedName("currencySymbol")
    val currencySymbol: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("imageLocation")
    val imageURL: String,
    @SerializedName("status")
    val productStatus: String
)