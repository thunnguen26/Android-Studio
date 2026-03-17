package com.example.myapplication.lab6.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun SelectOptionScreen(
    subtotal: String,
    options: List<Pair<String, String>>,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedValue by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedValue == item.second,
                            onClick = {
                                selectedValue = item.second
                                onSelectionChanged(item.second)
                            }
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item.second,
                        onClick = {
                            selectedValue = item.second
                            onSelectionChanged(item.second)
                        }
                    )
                    Text(item.first)
                }
            }

            Divider(
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = stringResource(R.string.subtotal_price, subtotal),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, bottom = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }

            Button(
                modifier = Modifier.weight(1f),
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

@Preview
@Composable
fun SelectOptionPreview() {
    SelectOptionScreen(
        subtotal = "$12.00",
        options = listOf(
            "Vanilla" to "Vanilla",
            "Chocolate" to "Chocolate",
            "Red Velvet" to "Red Velvet"
        ),
        modifier = Modifier.fillMaxHeight()
    )
}