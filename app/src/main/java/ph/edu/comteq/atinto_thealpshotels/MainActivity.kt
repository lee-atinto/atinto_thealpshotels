package ph.edu.comteq.atinto_thealpshotels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.comteq.atinto_thealpshotels.ui.theme.Atinto_thealpshotelsTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Atinto_thealpshotelsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Homepage(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Homepage(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var hotels by remember { mutableStateOf(emptyList<Hotel>()) }

    LaunchedEffect(Unit) {
        try {
            val json = context.assets.open("hotels.json")
                .bufferedReader()
                .use { it.readText() }
            val gson = Gson()
            val hotelsArray = gson.fromJson(json, classOfT = Array<Hotel>::class.java)
            hotels = hotelsArray.toList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "The Alps Hotels",
                modifier = Modifier.padding(end = 8.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.france_national_flag),
                contentDescription = "France National Flag",
                modifier = Modifier.size(24.dp)
            )
        }

        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search Hotel") },
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )
    }
}

@Composable
fun Gson() {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Atinto_thealpshotelsTheme {
        Homepage("Android")
    }
}
