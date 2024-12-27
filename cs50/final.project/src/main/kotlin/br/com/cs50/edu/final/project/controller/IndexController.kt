package br.com.cs50.edu.final.project.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value = ["/", "/index", "/index.html"])
@Controller
class IndexController {

    fun index(): String {
        return "index"
    }
}