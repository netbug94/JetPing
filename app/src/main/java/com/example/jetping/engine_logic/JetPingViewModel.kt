package com.example.jetping.engine_logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class JetPingViewModel : ViewModel() {
    var fieldValue by mutableStateOf("")
    var pingResults by mutableStateOf(emptyList<String>())

    suspend fun performPing() {
        try {
            val count = "5"
            val packetSize = "64"
            val command = "ping -c $count -s $packetSize $fieldValue"
            val process = withContext(Dispatchers.IO) {
                Runtime.getRuntime().exec(command)
            }

            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))
            val stringBuilder = StringBuilder()
            var line: String?

            while (withContext(Dispatchers.IO) { reader.readLine() }.also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }

            while (withContext(Dispatchers.IO) { errorReader.readLine() }.also { line = it } != null) {
                stringBuilder.append(line).append('\n')
            }

            val exitValue = withContext(Dispatchers.IO) { process.waitFor() }

            val result = if (exitValue == 0) {
                "Ping successful to $fieldValue\n$stringBuilder"
            } else {
                "Ping failed for $fieldValue\n$stringBuilder"
            }

            pingResults = pingResults + result
        } catch (e: Exception) {
            pingResults = pingResults + "Ping failed for $fieldValue: ${e.message}"
        }
    }
}