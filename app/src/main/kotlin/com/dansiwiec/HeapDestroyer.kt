package com.dansiwiec

import org.slf4j.LoggerFactory

val HEAP_TO_FILL: Int = (System.getenv("HEAP_TO_FILL") ?: "350").toInt()
val INCREMENTS_IN_MB = (System.getenv("INCREMENTS_IN_MB") ?: "50").toInt()

private val logger = LoggerFactory.getLogger(HeapDestroyer::class.java)
const val BYTES_TO_MB = 1024 * 1024

class HeapDestroyer

fun main(args: Array<String>) {
    blowHeap()
}

val list = mutableListOf<ByteArray>()

fun blowHeap() {
    printHeader()
    generateSequence(1) { it + 1 }
        .onEach { logMemoryStats() }
        .onEach { if (it % (HEAP_TO_FILL/INCREMENTS_IN_MB) == 0) { clearState() } }
        .onEach { Thread.sleep(1000L / 20) }
        .forEach { list.add(ByteArray(INCREMENTS_IN_MB * BYTES_TO_MB)) }
}

private fun clearState() {
    list.clear(); logger.info("State cleared at ~ $HEAP_TO_FILL MB.\n")
    printHeader()
}

private fun printHeader() {
    logger.info("         Used          Free            Total")
}

private fun logMemoryStats() {
    val rt = Runtime.getRuntime()
    val free = (rt.freeMemory() / BYTES_TO_MB).toDouble()
    val total = (rt.totalMemory() / BYTES_TO_MB).toDouble()
    val used = total - (rt.freeMemory() / BYTES_TO_MB).toDouble()
    logger.info("${"%10.2f".format(used)} MB\t${"%10.2f".format(free)} MB\t ${"%10.2f".format(total)} MB")
}
