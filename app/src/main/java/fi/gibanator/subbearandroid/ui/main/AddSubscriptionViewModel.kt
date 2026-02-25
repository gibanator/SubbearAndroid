package fi.gibanator.subbearandroid.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fi.gibanator.subbearandroid.data.PeriodUnit
import fi.gibanator.subbearandroid.data.Subscription
import fi.gibanator.subbearandroid.data.SubscriptionRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddSubscriptionViewModel @Inject constructor(
    private val subsRepo: SubscriptionRepo
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddSubscriptionUiState())
    val uiState: StateFlow<AddSubscriptionUiState> = _uiState.asStateFlow()

    fun addSubscription() {
        val state = _uiState.value

        val price = state.price.toDoubleOrNull() ?: return
        val newSubscription =
            Subscription(
                name = state.name,
                price = price,
                logoUri = "",
                periodUnit = PeriodUnit.MONTH,
                unitCount = 1,
                startDate = LocalDate.now(),
                nextCharge = LocalDate.now()
            )
        viewModelScope.launch {
            subsRepo.add(newSubscription)
        }
    }


    fun onFieldBlurred(field: Field) {
        _uiState.update { it.copy(touchedFields = it.touchedFields + field) }
    }

    fun onNameChanged(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun onPriceChanged(price: String) {
        _uiState.update { it.copy(price = price) }
    }
}