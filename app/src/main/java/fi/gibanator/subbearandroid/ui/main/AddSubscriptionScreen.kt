package fi.gibanator.subbearandroid.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@Composable
fun AddSubscriptionScreen(
    vm: AddSubscriptionViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    Column {
        AddSubscriptionForm(
            name = state.name,
            price = state.price,
            onNameChanged = vm::onNameChanged,
            onPriceChanged = vm::onPriceChanged,
            showNameError = state.showNameError,
            showPriceError = state.showPriceError,
            onFieldBlurred = vm::onFieldBlurred
        )

        ActionButtonRow(
            onCancelPress = { navController.popBackStack() },
            onAddPress = { vm.addSubscription() },
            isFormValid = state.isFormValid
        )
    }
}

@Composable
private fun AddSubscriptionForm(
    name: String = "",
    price: String = "",
    onNameChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    showNameError: Boolean,
    showPriceError: Boolean,
    onFieldBlurred: (Field) -> Unit = {}
){
    var nameHadFocus by remember { mutableStateOf(false) }
    var priceHadFocus by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = name,
            onValueChange = {
                onNameChanged(it)
            },
            label = { Text("Subscription Name") },
            isError = showNameError,
            modifier = Modifier.onFocusChanged { focusState ->
                if (focusState.isFocused) nameHadFocus = true
                else if (nameHadFocus) onFieldBlurred(Field.NAME)
            }
        )

        TextField(
            value = price,
            onValueChange = {
                onPriceChanged(it)
            },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = showPriceError,
            modifier = Modifier.onFocusChanged { focusState ->
                if (focusState.isFocused) priceHadFocus = true
                else if (priceHadFocus) onFieldBlurred(Field.PRICE)
            }
        )
    }
}

@Composable
private fun ActionButtonRow(
    onCancelPress: () -> Unit,
    onAddPress: () -> Unit,
    isFormValid: Boolean
){
    Row {
        Button(
            onClick = onCancelPress,
            enabled = true
        ) {
            Text("Cancel")
        }
        Button(
            onClick = onAddPress,
            enabled = isFormValid
        ) {
            Text("Save")
        }
    }
}