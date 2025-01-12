package com.example.em_dagger_task1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.em_dagger_task1.ui.theme.Emdaggertask1Theme
import com.example.feature.home.R
import com.example.feature.home.presentation.HomeScreen
import com.example.feature.home.presentation.HomeViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeComponent = (application as App).homeComponent

        //Передаем @Assisted время обновления с использованием AssistedFactory
        viewModel = homeComponent.viewModelFactory().create(10L)

        enableEdgeToEdge()
        setContent {
            Emdaggertask1Theme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = Color(0xfff9ee2a)
                ) { innerPadding ->
                    Image(
                        painter = painterResource(id = R.drawable.br_cats),
                        contentDescription = "Header main image",
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        HomeScreen(
                            viewModel = viewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                }
            }
        }
    }
}