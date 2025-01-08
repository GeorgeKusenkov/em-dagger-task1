package com.example.feature.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val cats by viewModel.catState.collectAsState()
    val quotes by viewModel.quoteState.collectAsState()

    Column {
        HomeContent(
            viewModel = viewModel,
            catsState = cats,
            quotesState = quotes,
            modifier = modifier
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    catsState: UiState<String>,
    quotesState: UiState<String>,
    modifier: Modifier = Modifier
) {
    when {
        catsState is UiState.Error -> {
            ErrorMessage(text = "No cats found!", modifier = modifier, viewModel = viewModel)
        }

        quotesState is UiState.Error -> {
            ErrorMessage(text = "No quotes found!", modifier = modifier, viewModel = viewModel)
        }

        catsState is UiState.Success && quotesState is UiState.Success -> {
            SuccessContent(
                viewModel = viewModel,
                catImageUrl = catsState.data,
                quoteText = quotesState.data,
                modifier = modifier
            )

        }

        else -> {
            LoadingContent(modifier = modifier)
        }
    }
}

@Composable
fun ErrorMessage(
    viewModel: HomeViewModel,
    text: String,
    modifier: Modifier = Modifier
) {
    Text(text = text, modifier = modifier.padding(16.dp))

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        onClick = {
            viewModel.update()
        }) {
        Text("Try again")
    }
}

@Composable
fun SuccessContent(
    viewModel: HomeViewModel,
    catImageUrl: String,
    quoteText: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
        .padding(30.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xff28724e))
        ) {
            Column {
                Box {
                    AsyncImage(
                        model = catImageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = "\"${quoteText}\"",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            onClick = {
                viewModel.update()
            }
        ) {
            Text("New Bad Cat Quote")
        }
    }

}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}