package com.example.timeinclusionchecker.ui.check

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.*
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timeinclusionchecker.R
import com.example.timeinclusionchecker.ui.AppViewModelProvider
import com.example.timeinclusionchecker.ui.theme.TimeInclusionCheckerTheme
import kotlinx.coroutines.launch

/**
 * チェック画面
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CheckScreen(
    checkViewModel: CheckViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNextButtonClicked: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    val uiState by checkViewModel.uiState.collectAsState()

    var firstTimeExpanded by remember { mutableStateOf(false) }
    var lastTimeExpanded by remember { mutableStateOf(false) }
    var targetTimeExpanded by remember { mutableStateOf(false) }
    var firstTimeSelectedNumber by rememberSaveable { mutableStateOf("選択してください") }
    var lastTimeSelectedNumber by rememberSaveable { mutableStateOf("選択してください") }
    var targetTimeSelectedNumber by rememberSaveable { mutableStateOf("選択してください") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNextButtonClicked,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Image(
                    painter = painterResource(R.drawable.baseline_history_24),
                    contentDescription = stringResource(R.string.view_histories_list),
                    modifier = Modifier.width(50.dp)
                )
            }
        },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            Text(
                text = stringResource(R.string.set_time_range),
                style = MaterialTheme.typography.headlineSmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // 最初の時間
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.input_first_time),
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                    TextButton(onClick = { firstTimeExpanded = true }) {
                        Text(
                            text = if (firstTimeSelectedNumber == "選択してください") {
                                firstTimeSelectedNumber
                            } else {
                                firstTimeSelectedNumber + "時"
                            },
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    DropdownMenu(
                        expanded = firstTimeExpanded,
                        onDismissRequest = { firstTimeExpanded = false }
                    ) {
                        (0..23).forEach { number ->

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = number.toString() + "時",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                },
                                onClick = {
                                    firstTimeSelectedNumber = number.toString()
                                    firstTimeExpanded = false
                                    checkViewModel.updateStartTime(number.toString())
                                },

                                )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 最後の時間
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.input_last_time),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                    TextButton(onClick = { lastTimeExpanded = true }) {
                        Text(
                            text = if (lastTimeSelectedNumber == "選択してください") {
                                lastTimeSelectedNumber
                            } else {
                                lastTimeSelectedNumber + "時"
                            },
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    DropdownMenu(
                        expanded = lastTimeExpanded,
                        onDismissRequest = { lastTimeExpanded = false }
                    ) {
                        (0..23).forEach { number ->

                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = number.toString() + "時",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                },
                                onClick = {
                                    lastTimeSelectedNumber = number.toString()
                                    lastTimeExpanded = false
                                    checkViewModel.updateLastTime(number.toString())
                                }
                            )
                        }
                    }
                }

            }

            // 調査対象時間
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(170.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    text = stringResource(R.string.input_target_time),
                    style = MaterialTheme.typography.headlineSmall
                )

                TextButton(
                    onClick = { targetTimeExpanded = true },
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally),
                ) {
                    Text(
                        text = if (targetTimeSelectedNumber == "選択してください") {
                            targetTimeSelectedNumber
                        } else {
                            targetTimeSelectedNumber + "時"
                        },
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                DropdownMenu(
                    expanded = targetTimeExpanded,
                    onDismissRequest = { targetTimeExpanded = false }
                ) {
                    (0..23).forEach { number ->

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = number.toString() + "時",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            },
                            onClick = {
                                targetTimeSelectedNumber = number.toString()
                                targetTimeExpanded = false
                                checkViewModel.updateTargetTime(number.toString())
                            }
                        )
                    }
                }

            }

            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
            ) {

                // 確認するボタン
                Button(
                    onClick = {
                        val checkResult = checkViewModel.checkInRange()
                        if (checkResult) {
                            coroutineScope.launch {
                                checkViewModel.saveHistory()
                            }
                        }
                    },
                    shape = MaterialTheme.shapes.small,
                    enabled = true
                ) {
                    Text(
                        text = stringResource(R.string.check_button),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }

                Card(
                    modifier = Modifier.padding(20.dp).width(300.dp),

                ) {
                    Text(
                        text = if (uiState.isInRange.isEmpty()) {
                            stringResource(R.string.check_result_unsettled)
                        } else {
                            stringResource(R.string.check_result) + uiState.isInRange
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(8.dp).align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun StartOrderPreview() {
    TimeInclusionCheckerTheme {
        CheckScreen(
            onNextButtonClicked = {}
        )
    }
}
