package com.example.mapview

import Model.Place
import Model.Places
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreen(onNextButtonClicked: (Place) -> Unit = {}) {
    Surface (modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.height(500.dp)
        ) {
            items(Places) { place ->
                Card (modifier = Modifier.padding(40.dp)){
                    Button(onClick = {onNextButtonClicked(place) }, shape = RectangleShape,colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Spacer(modifier = Modifier.size(20.dp))
                            Image(painter = painterResource(id = place.imageRes), contentDescription = "", contentScale = ContentScale.FillWidth)
                            Text(text = place.description, modifier = Modifier.padding(20.dp), color = Color.Black)
                        }
                    }

                }
            }
        }
    }
}
