package com.zhgo.community.advice;

import com.alibaba.fastjson.JSON;
import com.zhgo.community.dto.ResultDTO;
import com.zhgo.community.exception.CustomizeErrorCode;
import com.zhgo.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){

            ResultDTO resultDTO;
            if(e instanceof CustomizeException){
                 resultDTO = ResultDTO.okOf((CustomizeException) e);
            }else {
                 resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return  null;

        }else {

            if(e instanceof CustomizeException){
                model.addAttribute("massage",e.getMessage());
            }else {
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }


}
