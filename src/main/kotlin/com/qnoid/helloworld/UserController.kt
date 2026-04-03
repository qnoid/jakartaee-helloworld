package com.qnoid.helloworld

import jakarta.inject.Inject
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.ByteArrayOutputStream

@WebServlet("/user")
class UserController : HttpServlet() {

    @Inject
    lateinit var entityManager: StatelessEntityManager

    private val thymeleafWriter by lazy { ThymeleafWriter(servletContext) }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        val user = User()
        this.entityManager.persist(user)

        val substitutions = mutableMapOf<String, Any>()
        substitutions["id"] = user.id!!
        substitutions["identifier"] = user.identifier

        val body = thymeleafWriter.substitute("user", req, res, substitutions)

        write(res, body, "text/html; charset=utf-8")
    }

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val users: List<User> = this.entityManager.getResultList("user.list", resultClass = User::class.java)

        val substitutions = mutableMapOf<String, Any>()
        substitutions["users"] = users

        val body = thymeleafWriter.substitute("users", req, res, substitutions)

        write(res, body, "text/html; charset=utf-8")
    }

    private fun write(res: HttpServletResponse, body: ByteArrayOutputStream, contentType: String) {
        res.contentType = contentType
        res.setContentLength(body.size())
        res.writer.write(body.toString(Charsets.UTF_8))
    }
}
