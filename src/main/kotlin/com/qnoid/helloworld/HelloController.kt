package com.qnoid.helloworld

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet("/hello")
class HelloController : HttpServlet() {

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val name = req.getParameter("name")
        val message = "Hello $name!👋🏽"

        res.setContentLength(message.count())
        res.contentType = "text/html; charset=utf-8"
        res.writer.write(message)
    }
}