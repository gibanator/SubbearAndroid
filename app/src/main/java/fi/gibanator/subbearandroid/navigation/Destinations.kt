package fi.gibanator.subbearandroid.navigation

sealed interface AppDestination {
    val route: String
    val title: String? get() = null
}

object UpcomingSubscriptions : AppDestination {
    override val route = "upcoming_subscriptions"
    override val title = "Subbear"
}

object AddSubscription : AppDestination {
    override val route = "add_subscription"
    override val title = "Add a subscription"
}
