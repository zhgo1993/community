package com.zhgo.community.controller;

import com.zhgo.community.dto.CommentDTO;
import com.zhgo.community.dto.QuestionDTO;
import com.zhgo.community.enums.CommentTypeEnum;
import com.zhgo.community.model.Question;
import com.zhgo.community.service.CommentService;
import com.zhgo.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id")Integer id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);

        List<QuestionDTO> relateQuestionDTOs = questionService.selectRelated(questionDTO);

        List<CommentDTO> commentDTOS =  commentService.listByTargetId(id, CommentTypeEnum.QUESTION);

        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("commentDTOS",commentDTOS);
        model.addAttribute("relateQuestionDTOs",relateQuestionDTOs);
        return "question";
    }
}
