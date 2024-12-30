package br.com.cs50.edu.finalproject.controller

import br.com.cs50.edu.finalproject.model.DocAttribute
import br.com.cs50.edu.finalproject.repository.DocumentRepository
import br.com.cs50.edu.finalproject.model.Document
import br.com.cs50.edu.finalproject.service.OllamaService
import br.com.cs50.edu.finalproject.tess.Tesseract
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import java.io.ByteArrayInputStream
import java.time.format.DateTimeFormatter
import java.util.*
import javax.imageio.ImageIO

@Controller
@RequestMapping("/documents")
class DocumentController(
    val repository: DocumentRepository
) {

    @PostMapping
    fun extractText(@RequestParam("file") file: MultipartFile): ResponseEntity<Any> {
        if (file.isEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to "File is empty"))
        }

        val document = Document(
            fileName = file.originalFilename ?: "unknown",
            blob = file.bytes
        )

        repository.save(document)

        println("Extracting text from image...")
        val tess = Tesseract()

        val img = ImageIO.read(ByteArrayInputStream(file.bytes))

        val txt = tess.extractText(img)

        println("Image successfully processed with tesseract.")

        println("Starting text processing...")
        val result = OllamaService.prompt(txt)

        println("Text successfully processed with ollama.")

        val mapper = ObjectMapper()
        val tree = mapper.readTree(result)

        val invoiceNumber = tree["invoice_number"].asText()
        val invoiceDate = tree["invoice_date"].asText()
        val total = tree["total"].asText()

        document.attributes.add(DocAttribute(name = "invoice_number", value = invoiceNumber, documentId = document.id!!))
        document.attributes.add(DocAttribute(name = "invoice_date", value = invoiceDate, documentId = document.id!!))
        document.attributes.add(DocAttribute(name = "total", value = total, documentId = document.id!!))

        repository.save(document)

        return ResponseEntity.ok(result)
    }

    @GetMapping
    fun listDocuments(model: Model): String {
        val documents = repository.findAll().map {
            DocumentDTO(
                id = it.id!!,
                blob = Base64.getEncoder().encodeToString(it.blob),
                fileName = it.fileName,
                creationDate= it.createdAt.format(DateTimeFormatter.ISO_WEEK_DATE),
                invoiceNumber = it.attributes.firstOrNull { a -> a.name == "invoice_number" }?.value ?: "",
                invoiceDate = it.attributes.firstOrNull { a -> a.name == "invoice_date" }?.value ?: "",
                total = it.attributes.firstOrNull { a -> a.name == "total" }?.value ?: "",
            )
        }

        model.addAttribute("documents", documents)
        return "list"
    }


    @GetMapping("/{id}")
    fun detail(@PathVariable id: Int, model: Model): String {
        val document = repository.findById(id).orElseThrow { RuntimeException("Document not found") }

        val documentDTO = DocumentDTO(
            id = document.id!!,
            blob = Base64.getEncoder().encodeToString(document.blob),
            fileName = document.fileName,
            creationDate = document.createdAt.format(DateTimeFormatter.ISO_DATE),
            invoiceNumber = document.attributes.firstOrNull { it.name == "invoice_number" }?.value ?: "",
            invoiceDate = document.attributes.firstOrNull { it.name == "invoice_date" }?.value ?: "",
            total = document.attributes.firstOrNull { it.name == "total" }?.value ?: "",
        )

        model.addAttribute("document", documentDTO)
        return "detail"
    }
}

data class DocumentDTO (
    val id: Int,
    val blob: String,
    val fileName: String,
    val creationDate: String,
    val invoiceNumber: String,
    val invoiceDate: String,
    val total: String,
)
