package inc.sims.hustles.carry1st.core.navigation

sealed class Screen(val route: String) {
    object ProductList: Screen("product_list")
    object ProductDetail: Screen("product_detail")
    object OrderSummary: Screen("order_summary")
}