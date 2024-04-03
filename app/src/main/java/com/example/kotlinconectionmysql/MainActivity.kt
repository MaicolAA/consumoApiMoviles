package com.example.kotlinconectionmysql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kotlinconectionmysql.ui.theme.KotlinconectionmysqlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val queue = Volley.newRequestQueue(this)

        setContent {
            MainActivityContent(queue)
        }
    }
}

@Composable
fun MainActivityContent(queue: RequestQueue) {
    KotlinconectionmysqlTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            var apiResult by remember { mutableStateOf("") }

            LaunchedEffect(true) {
                val url = "http://localhost/apiMoviles0104/apiConection.php"

                val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener { response ->
                        apiResult = response
                    },
                    Response.ErrorListener { error -> })

                queue.add(stringRequest)
            }

            Greeting(apiResult)
        }
    }
}

@Composable
fun Greeting(apiResult: String, modifier: Modifier = Modifier) {
    Text(
        text = apiResult,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainActivityContent(Volley.newRequestQueue(LocalContext.current))
}
