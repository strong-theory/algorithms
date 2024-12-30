package br.com.cs50.edu.finalproject.model

import jakarta.persistence.*

@Entity
@Table(name = "doc_attributes")
class DocAttribute(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "document_id")
    @JoinColumn(name = "document_id")
    val documentId: Int,

    @Column(name = "name")
    val name: String,

    @Column(name = "\"value\"")
    val value: String

)
