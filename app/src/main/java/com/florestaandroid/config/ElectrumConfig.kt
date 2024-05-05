package com.florestaandroid.config

/*
private lateinit var daemon: Florestad


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

t.start()*/
