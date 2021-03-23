package com.zhgo.community.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorControlle implements ErrorController {
    @Override
    public String getErrorPath() {

        return "error";
    }
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model) {
        HttpStatus status = getStatus(request);
        if(status.is4xxClientError()){
            model.addAttribute("message","客户端请求出错！！！");
        }
        if(status.is5xxServerError()){
            model.addAttribute("message","服务器端处理出问题了！！！");
        }
        return new ModelAndView("error");
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
