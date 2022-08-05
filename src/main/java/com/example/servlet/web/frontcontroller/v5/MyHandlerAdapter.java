package com.example.servlet.web.frontcontroller.v5;

import com.example.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    boolean supports(Object object);

    ModelView handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws ServletException, IOException;

}
