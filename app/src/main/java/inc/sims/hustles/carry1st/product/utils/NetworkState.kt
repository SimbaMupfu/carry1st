package inc.sims.hustles.carry1st.product.utils

sealed class NetworkState<out T> {
    data class Success<out T>(val data: T) : NetworkState<T>()
    data class Error(val message: String, val code: Int? = null) : NetworkState<Nothing>()
    data object Loading : NetworkState<Nothing>()
}