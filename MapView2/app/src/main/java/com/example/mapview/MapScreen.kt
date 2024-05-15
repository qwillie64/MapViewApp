package com.example.mapview

import Model.Place
import Model.Places
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MapScreen(current: Place = Places[0], onBackButtonClicked: () -> Unit = {},
              modifier: Modifier = Modifier){
    Surface(modifier = Modifier.fillMaxSize()){
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
//                    settings.loadWithOverviewMode = true
//                    settings.useWideViewPort = true
//                    settings.setSupportZoom(true)
//                    loadUrl(current.location)
                    loadData(current.location, "text/html", null)
                }
            },modifier = Modifier.fillMaxWidth()
        )
    }
}
