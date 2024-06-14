package com.florestaandroid.data

private const val HEX_CHARS = "0123456789ABCDEF"
private val HEX_CHARS_ARRAY = "0123456789ABCDEF".toCharArray()

fun String.hexStringToByteArray() : ByteArray {
    val HEX_CHARS = "0123456789ABCDEF"

    val result = ByteArray(this.length / 2)

    for (i in 0 until this.length step 2) {
        val firstIndex = HEX_CHARS.indexOf(this[i])
        val secondIndex = HEX_CHARS.indexOf(this[i + 1])

        val octet = firstIndex.shl(4).or(secondIndex)
        result[i.shr(1)] = octet.toByte()
    }

    return result
}

fun List<UByte>.toHexString(): String = joinToString("") { it.toString(16).padStart(2, '0') }

fun ByteArray.toHexString() : String {
    val result = StringBuffer()

    this.forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS_ARRAY[firstIndex])
        result.append(HEX_CHARS_ARRAY[secondIndex])
    }

    return result.toString().uppercase()
}