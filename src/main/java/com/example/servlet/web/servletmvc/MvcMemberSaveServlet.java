package com.example.servlet.web.servletmvc;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        //model에 데이터를 보관한다.
        req.setAttribute("member",member);

        String viewpath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewpath);
        requestDispatcher.forward(req,resp);
    }
}
