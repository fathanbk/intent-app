package com.example.intentapp

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.intentapp.ui.theme.IntentAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentAppTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                   
                ) {
                    viewModel.uri?.let{
                        AsyncImage(
                            model = viewModel.uri,
                            contentDescription = null)
                    }
                    Button(onClick = {
//                        Intent(applicationContext, SecondActivity::class.java).also {
//                            startActivity(it)
//                        }
//                        Intent(Intent.ACTION_MAIN).also {
//                            it.`package` = "com.google.android.youtube"
//                           startActivity(it)
//                        }
                        val intent =Intent(Intent.ACTION_SEND).apply {
                            type= "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("test@mail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                            putExtra(Intent.EXTRA_TEXT, "This is my content")
                        }
                        if (intent.resolveActivity(packageManager) != null){
                            startActivity(intent)
                        }
                    }) {
                        Text(text = "Navigate", fontSize = 18.sp)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val uri = intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        viewModel.updateUri(uri)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentAppTheme {
        Greeting("Android")
    }
}