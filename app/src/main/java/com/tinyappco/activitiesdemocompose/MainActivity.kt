package com.tinyappco.activitiesdemocompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tinyappco.activitiesdemocompose.ui.theme.ActivitiesDemoComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivitiesDemoComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Launcher()
                }
            }
        }
    }


    @Preview
    @Composable
    fun Launcher() {
        var nameFromOtherActivity by remember { mutableStateOf<String?>(null) }
        val resultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val name = result.data?.getStringExtra("name")
            if (name != null) {
                nameFromOtherActivity = name
            }
        }
        Column {
            Button(
                onClick = {
                    val intent = Intent(this@MainActivity, DataEntryActivity::class.java) //note this@MainActivity to escape the column's scope
                    resultLauncher.launch(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Input data")
            }
            Text(nameFromOtherActivity ?: "")
        }
    }

}


