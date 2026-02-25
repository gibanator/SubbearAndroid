package fi.gibanator.subbearandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fi.gibanator.subbearandroid.data.Subscription
import fi.gibanator.subbearandroid.data.SubscriptionRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingSubscriptionViewModel @Inject constructor(
    private val repo: SubscriptionRepo
) : ViewModel() {
    private val _subCards = MutableStateFlow<List<Subscription>>(emptyList())
    val subCards = _subCards.asStateFlow()

    init {
         loadSubscriptions()
    }

    private fun loadSubscriptions() {
        viewModelScope.launch {
            repo.getAllItemsStream().collectLatest {  li ->
                _subCards.value = li
            }
        }
    }
}