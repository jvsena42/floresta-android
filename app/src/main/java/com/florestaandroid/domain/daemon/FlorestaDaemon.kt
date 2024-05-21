package com.florestaandroid.domain.daemon

import android.app.Application
import com.florestaandroid.config.FlorestaApplication
import uniffi.floresta.Config
import uniffi.floresta.Florestad
import uniffi.floresta.Network
import javax.inject.Inject

interface FlorestaDaemon {
    fun start()
    fun stop()
}

class FlorestaDaemonImpl @Inject constructor(
    private val application: Application
) : FlorestaDaemon {

    private lateinit var daemon: Florestad
    override fun start() {
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
    }

    override fun stop() {
        daemon.stop()
    }

}