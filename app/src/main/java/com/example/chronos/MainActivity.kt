package com.example.chronos

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.chronos.ui.theme.ChronosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChronosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(48.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        FilledButtonExample(onClick = {
                            Log.d("Filled button", "Filled button clicked.")
                            var testEvent = TimelineEvent("newEvent", 0)
                            testEvent.logUuid()
                            testEvent.eventName = "Name 2"
                            testEvent.logNotes()
                        })

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

// todo: on click, generate a popup window with text field to input a name, which will create a new timeline event
@Composable
fun FilledButtonExample(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Generate New Timeline Event")
    }
}

@Preview
@Composable
fun StateBasedTextSnippets() {
    OutlinedTextField(
        state = rememberTextFieldState(),
        lineLimits = TextFieldLineLimits.SingleLine,
        label = { Text("Label") },
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChronosTheme {
        Greeting("Android")
    }
}