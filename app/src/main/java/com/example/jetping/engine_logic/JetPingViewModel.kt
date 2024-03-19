package com.example.jetping.engine_logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class JetPingViewModel : ViewModel() {
    var fieldValue by mutableStateOf("")
    var pingResults by mutableStateOf(listOf<String>())
    var isLoading by mutableStateOf(false)

    fun performPing() {
        viewModelScope.launch {
            isLoading = true // Set loading to true before starting the operation
            val newResult = try {
                val count = "5"
                val packetSize = "64"
                val command = "ping -c $count -s $packetSize $fieldValue"
                val output = executeCommand(command)
                output
            } catch (e: Exception) {
                "Ping failed: ${e.message}"
            } finally {
                isLoading = false // Set loading to false after the operation
            }
            pingResults = pingResults + newResult
        }
    }

    private suspend fun executeCommand(command: String): String = withContext(Dispatchers.IO) {
        val process = ProcessBuilder(*command.split(" ").toTypedArray())
            .redirectErrorStream(true)
            .start()

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val output = reader.readText()
        process.waitFor()
        output
    }
    fun resetValues() {
        fieldValue = ""
        pingResults = listOf("")
    }
}