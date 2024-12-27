package br.com.cs50.edu.final.project.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Document")
class Document(
    @Id
    @GeneratedValue
    val id: Int? = null,

    @Column(name = "file_name")
    val fileName: String,

    @Column(name = "blob", columnDefinition = "BLOB")
    val blob: ByteArray,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "documentId", cascade = [CascadeType.ALL], orphanRemoval = true)
    val attributes: MutableSet<DocAttribute> = mutableSetOf()

)
