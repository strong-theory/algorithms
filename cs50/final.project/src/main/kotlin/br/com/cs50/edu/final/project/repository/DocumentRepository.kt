package br.com.cs50.edu.final.project.repository

import br.com.cs50.edu.final.project.model.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository : JpaRepository<Document, Int> {

}