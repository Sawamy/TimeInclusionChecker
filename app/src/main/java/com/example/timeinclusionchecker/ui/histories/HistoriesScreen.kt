package com.example.timeinclusionchecker.ui.histories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timeinclusionchecker.R
import com.example.timeinclusionchecker.data.History
import com.example.timeinclusionchecker.ui.AppViewModelProvider
import com.example.timeinclusionchecker.ui.theme.TimeInclusionCheckerTheme

@Composable
fun HistoriesScreen(
//    itemList: List<History>,
    historiesViewModel: HistoriesViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {

    val historiesUiState by historiesViewModel.historiesUiState.collectAsState()

    HistoryBody(
        historyList = historiesUiState.historiesList,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun HistoryBody(
    historyList: List<History>,
    modifier: Modifier = Modifier,
) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (historyList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
            )
        } else {
            InventoryList(
                historyList = historyList
            )
        }
    }
}

@Composable
private fun InventoryList(
    historyList: List<History>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = historyList, key = { it.id }) { item ->
            HistoryItem(
                history = item,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun HistoryItem(
    history: History, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.histories_no) + history.id,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    text = history.checkTime,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.histories_start_time) + history.startTime + stringResource(R.string.histories_hour),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.histories_last_time) + history.lastTime + stringResource(R.string.histories_hour),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.histories_target_time) + history.targetTime + stringResource(R.string.histories_hour),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Start,
                    text = stringResource(R.string.histories_is_in_range) + history.isInRange,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoriesScreenPreview() {
    TimeInclusionCheckerTheme {
        HistoriesScreen()
    }
}