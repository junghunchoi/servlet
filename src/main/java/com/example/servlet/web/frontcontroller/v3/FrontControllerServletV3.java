package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v2.ControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberFromControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import com.example.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import com.example.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="frontcontrollerServletv3" , urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {


    private Map<String , ControllerV3> controllerMap =new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String requestURI = req.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        // 가령 list를 반환한다고 하면 ControllerV1 controller = new MemberListControllerV1()); 와 같은 구문이 된다.
        if (controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        Map<String, String> paramMap = createParamMap(req);
        ModelView modelview = controller.process(paramMap);

        String viewName = modelview.getViewName();// 논리이름 new-form

        MyView view = viewResolver(viewName);
        view.render(modelview.getModel(), req,resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/"+viewName+".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String,String> paramMap = new HashMap<>();


        req.getParameterNames().asIterator().forEachRemaining(paramName -> paramMap.put(paramName,req.getParameter(paramName)));
        return paramMap;
    }
}
