package com.testfloresta.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import uniffi.floresta.Config
import uniffi.floresta.Florestad
import uniffi.floresta.Network
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setSupportActionBar(binding.toolbar)

    }

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestFlorestaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}*/
}