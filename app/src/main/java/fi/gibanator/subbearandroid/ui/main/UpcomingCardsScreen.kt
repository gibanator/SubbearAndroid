package fi.gibanator.subbearandroid.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.gibanator.subbearandroid.R

@Composable
fun SubscriptionList(
    //subs: List<Subscription>
    subs: List<String>,
    onSubscriptionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(subs.size) { sub ->
            SubscriptionCard(subs[sub], onSubscriptionClick)
        }
    }
}

@Composable
fun SubscriptionCard(
    cardText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.netflix),
                contentDescription = "SubImage",
                modifier = Modifier
                    .size(48.dp)
            )
            Spacer(
                modifier = Modifier.width(16.dp)
            )
            Text(
                cardText,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun SubCardPreview(){
    SubscriptionCard("Netflix", {})
}

@Preview(showBackground = true)
@Composable
fun SubListPreview(){
    val prevList: List<String> = listOf("Netflix", "Disney", "Spotify")
    SubscriptionList(prevList, {})
}