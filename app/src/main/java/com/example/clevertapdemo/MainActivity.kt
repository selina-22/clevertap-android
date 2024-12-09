package com.example.clevertapdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.clevertap.android.sdk.CleverTapAPI
import com.example.clevertapdemo.ui.theme.ClevertapDemoTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Get CleverTap instance
        val clevertap = CleverTapAPI.getDefaultInstance(applicationContext)

        // Track Product Viewed event on launch
        clevertap?.pushEvent("Product Viewed")

        setContent {
            ClevertapDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CleverTapDemo(
                        clevertap = clevertap,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CleverTapDemo(clevertap: CleverTapAPI?, modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val profileUpdate = HashMap<String, Any>()
                profileUpdate["Name"] = name
                profileUpdate["Email"] = email
                clevertap?.onUserLogin(profileUpdate)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("LOGIN")
        }

        Button(
            onClick = { clevertap?.pushEvent("TEST") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Raise TEST Event")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CleverTapDemoPreview() {
    ClevertapDemoTheme {
        CleverTapDemo(clevertap = null)
    }
}