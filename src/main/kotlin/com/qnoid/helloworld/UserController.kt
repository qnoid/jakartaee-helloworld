package com.qnoid.helloworld;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/user")
class UserController : HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

    }
}
