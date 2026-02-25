package fi.gibanator.subbearandroid.ui.main

data class AddSubscriptionUiState (
    val name: String = "",
    val price: String = "",
    val logoUri: String = "",
    val touchedFields: Set<Field> = emptySet()
)
enum class Field{
    NAME,
    PRICE
}

val AddSubscriptionUiState.isFormValid: Boolean
    get() = name.isNotBlank() && price.toDoubleOrNull() != null

val AddSubscriptionUiState.showNameError: Boolean
    get() = Field.NAME in touchedFields && name.isBlank()

val AddSubscriptionUiState.showPriceError: Boolean
    get() = Field.PRICE in touchedFields && price.toDoubleOrNull() == null