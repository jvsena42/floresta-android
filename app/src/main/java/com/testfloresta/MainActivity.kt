package com.testfloresta

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import uniffi.floresta.Config
import uniffi.floresta.Florestad
import uniffi.floresta.Network
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket

class MainActivity : FragmentActivity() {

    private lateinit var daemon: Florestad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setSupportActionBar(binding.toolbar)


        val datadir = application.dataDir.toString()
        val config = Config(
            false,
            listOf(),
            true,
            Network.SIGNET,
            datadir,
            electrumAddress = "127.0.0.1:50001",
            logToFile = true
        )
        daemon = Florestad.fromConfig(config)
        daemon.start()

        val t = Thread(fun() {
            while (true) {
                val client = Socket("127.0.0.1", 50001)
                client.outputStream.write(
                    "{\"jsonrpc\":\"2.0\",\"method\":\"blockchain.headers.subscribe\",\"params\":[],\"id\":1}\n"
                        .toByteArray()
                )
                val text = BufferedReader(InputStreamReader(client.inputStream)).readLine()
                Log.d("MAIN_ACTIVITY", "TEXT_: $text")
//                runOnUiThread {
//                    findViewById<TextView>(R.id.textView).text = text
//                }
                Thread.sleep(10000)
            }
        })

        t.start()
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

    override fun onDestroy() {
        daemon.stop()
        super.onDestroy()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}