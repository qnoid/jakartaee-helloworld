package com.qnoid.helloworld

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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