package com.example.servlet.web.frontcontroller.v2;

import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import com.example.servlet.web.frontcontroller.v2.controller.MemberFromControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontcontrollerServletv2" , urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {


    private Map<String , ControllerV2> controllerMap =new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFromControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String requestURI = req.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);
        // 가령 list를 반환한다고 하면 ControllerV1 controller = new MemberListControllerV1()); 와 같은 구문이 된다.
        if (controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(req, resp); // new Myview(viewpath);


        view.render(req,resp);
    }
}
