package br.com.cs50.edu.finalproject.tess

import net.sourceforge.tess4j.Tesseract;
import java.awt.image.BufferedImage

class Tesseract() {

    private val tesseract = Tesseract()

    init {
        tesseract.setLanguage("eng")
        tesseract.setDatapath("/usr/share/tesseract/tessdata")
    }

    fun extractText(img: BufferedImage): String {
        return tesseract.doOCR(img)
    }

}