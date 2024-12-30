package br.com.cs50.edu.finalproject.service

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets

object OllamaService {

    private const val MODEL = "llama3.1"

    fun prompt(message: String): String {
        val client = HttpClient.newHttpClient()

        val apiUrl = "http://localhost:11434/api/generate"

        val pmpt = """
            Extract the invoice information from the text below delimited by <<<< and >>>>:
            
            The information should be extracted as a JSON. And all the properties must be in snake_case.
            
            Just answer the raw JSON. Don't answer anything else. Return only the following properties: total, invoice_number, invoice_date
            $message
            >>>>
        """.trimIndent()

        val jsonBody = """
        {
          "model": "$MODEL",
          "prompt": "${pmpt.trimIndent().replace('\r', ' ').replace('\n', ' ')
            .replace("\"", "\\\"")}",
          "stream": true
        }
    """.trimIndent()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofInputStream())

        val status = response.statusCode()
        println("Response.status: $status")
        if (status != 200) {
            throw RuntimeException("Error ${response.statusCode()}")
        }

        val mapper = ObjectMapper()

        val str = StringBuilder()
        response.body().bufferedReader().lines().forEach {
            val apiResponse  = mapper.readValue(it, OllamaResponse::class.java)
            str.append(apiResponse.response)
        }

        return str.substring(str.indexOf('{'), str.indexOf('}') + 1)
    }

}



@JsonIgnoreProperties(ignoreUnknown = true)
data class OllamaResponse(
    @JsonProperty("model")
    var model: String = "",

    @JsonProperty("created_at")
    var createdAt: String = "",

    @JsonProperty("response")
    var response: String = "",

    @JsonProperty("done")
    var done: Boolean = false
)