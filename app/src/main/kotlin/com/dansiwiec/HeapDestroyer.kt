package com.dansiwiec

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

val HEAP_TO_FILL: Int = (System.getenv("HEAP_TO_FILL") ?: "350").toInt()
val INCREMENTS_IN_MB = (System.getenv("INCREMENTS_IN_MB") ?: "10").toInt()

private val logger = LoggerFactory.getLogger(HeapDestroyer::class.java)
const val BYTES_TO_MB = 1024 * 1024

@SpringBootApplication
class HeapDestroyer

fun main(args: Array<String>) {
    runApplication<HeapDestroyer>(*args)
    blowHeap()
}

val list = mutableListOf<ByteArray>()

fun blowHeap() {
    printHeader()
    generateSequence(0) { it + 1 }.forEach {
        logMemoryStats()
        if (it % (HEAP_TO_FILL / INCREMENTS_IN_MB) == 0) clearState()
        list.add(ByteArray(INCREMENTS_IN_MB * BYTES_TO_MB))
    }

}

private fun clearState() {
    list.clear(); logger.info("State cleared at ~ $HEAP_TO_FILL MB.\n")
    printHeader()
}

private fun printHeader() {
    logger.info("         Used          Free            Total")
}

private fun logMemoryStats() {
    Thread.sleep(1000L / 20)
    val rt = Runtime.getRuntime()
    val free = (rt.freeMemory() / BYTES_TO_MB).toDouble()
    val total = (rt.totalMemory() / BYTES_TO_MB).toDouble()
    val used = total - (rt.freeMemory() / BYTES_TO_MB).toDouble()
    logger.info("${"%10.2f".format(used)} MB\t${"%10.2f".format(free)} MB\t ${"%10.2f".format(total)} MB")
}
