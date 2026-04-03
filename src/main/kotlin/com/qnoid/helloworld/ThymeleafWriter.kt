package com.qnoid.helloworld

import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import jakarta.servlet.ServletContext
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.WebContext
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver
import org.thymeleaf.web.servlet.JakartaServletWebApplication

class ThymeleafWriter(servletContext: ServletContext) {

    private val webApplication = JakartaServletWebApplication.buildApplication(servletContext)
    private val templateEngine = TemplateEngine().apply {
        setTemplateResolver(WebApplicationTemplateResolver(webApplication).apply {
            prefix = "/WEB-INF/templates/"
            suffix = ".html"
            templateMode = TemplateMode.HTML
            characterEncoding = "UTF-8"
        })
    }

    fun substitute(
        template: String,
        req: HttpServletRequest,
        res: HttpServletResponse,
        substitutions: Map<String, Any>
    ): ByteArrayOutputStream {
        val webExchange = webApplication.buildExchange(req, res)
        val context = WebContext(webExchange, webExchange.locale, substitutions)
        val out = ByteArrayOutputStream()
        val writer = OutputStreamWriter(out, Charsets.UTF_8)

        templateEngine.process(template, context, writer)
        writer.flush()

        return out
    }
}
