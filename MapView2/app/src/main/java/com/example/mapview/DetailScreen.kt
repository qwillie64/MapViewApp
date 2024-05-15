package com.example.mapview

import Model.Place
import Model.Places
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailScreen(current:Place = Places[0], onBackButtonClicked: () -> Unit = {},
                 onNextButtonClicked: () -> Unit = {},
                 modifier: Modifier = Modifier){
    Surface (modifier = Modifier.fillMaxSize()){
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(current.imageRes), contentDescription = "",modifier = Modifier.size(300.dp, 200.dp), contentScale = ContentScale.FillBounds)
            Spacer(modifier = Modifier)
            Text(text = current.detail,
                modifier= Modifier.padding(20.dp))
            Button(onClick = { onNextButtonClicked() }, modifier = Modifier
                .padding(0.dp, 40.dp, 0.dp, 0.dp)
                .size(200.dp, 50.dp), shape = RectangleShape
            ){Text(text = "Go to map")}
        }
    }
}
