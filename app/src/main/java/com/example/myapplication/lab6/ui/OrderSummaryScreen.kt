package com.example.myapplication.lab6.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.lab6.data.OrderUiState

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = androidx.compose.ui.platform.LocalContext.current.resources

    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )

    val orderSummary = stringResource(
        R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.price
    )

    val newOrder = stringResource(R.string.new_cupcake_order)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.quantity),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = numberOfCupcakes)

            Divider(thickness = 1.dp)

            Text(
                text = stringResource(R.string.flavor),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = orderUiState.flavor)

            Divider(thickness = 1.dp)

            Text(
                text = stringResource(R.string.pickup_date),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = orderUiState.date)

            Divider(thickness = 1.dp)

            Text(
                text = stringResource(R.string.total_price, orderUiState.price),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.End)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = { onSendButtonClicked(newOrder, orderSummary) }
            ) {
                Text(stringResource(R.string.send))
            }
        }
    }
}

@Preview
@Composable
fun OrderSummaryPreview() {
    OrderSummaryScreen(
        orderUiState = OrderUiState(
            quantity = 6,
            flavor = "Chocolate",
            date = "Sat Jul 23",
            price = "$12.00"
        ),
        onCancelButtonClicked = {},
        onSendButtonClicked = { _, _ -> }
    )
}