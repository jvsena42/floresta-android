package com.florestaandroid.data.datasource

enum class BlockchainMethods(val method: String) {
    SUBSCRIBE_HEADER("blockchain.headers.subscribe"),
    GET_BALANCE("blockchain.scripthash.get_balance"),
}