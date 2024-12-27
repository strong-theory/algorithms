package br.com.cs50.edu.final.project.controller

import br.com.cs50.edu.final.project.repository.DocumentRepository
import br.com.cs50.edu.final.project.model.Document
import br.com.cs50.edu.final.project.tess.Tesseract
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
    fun extractText(@RequestParam("file") file: MultipartFile): ResponseEntity<Map<String, String>> {
        if (file.isEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to "File is empty"))
        }

        val document = Document(
            fileName = file.originalFilename ?: "unknown",
            blob = file.bytes
        )

        repository.save(document)

        val tess = Tesseract()

        val img = ImageIO.read(ByteArrayInputStream(file.bytes))

        val txt = tess.extractText(img)

        println(txt)

        // Simulate text extraction from PDF or image
        val extractedData = mapOf(
            "Invoice Number" to "12345",
            "Date" to "2024-12-26",
            "Amount" to "$500",
            "Vendor" to "ACME Corp."
        )

        return ResponseEntity.ok(extractedData)
    }

    @GetMapping
    fun listDocuments(model: Model): String {
        val documents = repository.findAll().map {
            DocumentDTO(
                id = it.id!!,
                blob = Base64.getEncoder().encodeToString(it.blob),
                fileName = it.fileName,
                creationDate= it.createdAt.format(DateTimeFormatter.ISO_WEEK_DATE),
                invoiceNumber = it.attributes.firstOrNull { a -> a.name == "InvoiceNumber" }?.name ?: ""
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
            creationDate = document.createdAt.format(DateTimeFormatter.ISO_WEEK_DATE),
            invoiceNumber = document.attributes.firstOrNull { it.name == "InvoiceNumber" }?.name ?: ""
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
)
