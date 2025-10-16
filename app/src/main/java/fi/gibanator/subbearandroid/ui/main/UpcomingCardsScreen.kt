package fi.gibanator.subbearandroid.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import fi.gibanator.subbearandroid.R
import fi.gibanator.subbearandroid.data.PeriodUnit
import fi.gibanator.subbearandroid.data.Subscription
import java.time.LocalDate


@Composable
fun UpcomingSubscriptionsScreen(
    modifier: Modifier = Modifier,
    vm: UpcomingSubscriptionViewModel = hiltViewModel()
) {
    val subs by vm.subCards.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        UpcomingSubscriptionsList(
            subs,
            {},
            modifier = Modifier.align(Alignment.Center)
        )
        GradientFab(
            { vm.addSubscription() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
        )
    }
}
@Composable
fun UpcomingSubscriptionsList(
    subs: List<Subscription>,
    onSubscriptionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(subs.size) { sub ->
            SubscriptionCard(subs[sub].name, onSubscriptionClick)
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
        modifier = modifier
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

@Composable
fun GradientFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC), Color(0xBB1427FF))
    )
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(gradient)
            .clickable(
                indication = ripple(bounded = false, radius = 40.dp),
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() }
            )
            .size(45.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Filled.Add, contentDescription = "", tint = Color.White)
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
    val prevList: List<Subscription> = listOf(
        Subscription(
            1, "aaa",
            logoUri = "",
            periodUnit = PeriodUnit.DAY,
            unitCount = 1,
            startDate = LocalDate.now(),
            nextCharge = LocalDate.now()
        )
    )
    UpcomingSubscriptionsList(prevList, {})
}

@Preview
@Composable
fun FabPreview(){
    GradientFab({})
}