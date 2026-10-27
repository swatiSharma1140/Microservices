package com.question.controller;

import com.question.entities.Question;
import com.question.services.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionServiceImpl questionService;

    @PostMapping
    public Question Create(@RequestBody Question question){
        return questionService.create(question);
    }

    @GetMapping
    public List<Question> getAll(){
        return questionService.get();
    }

    @GetMapping("/{Id}")
    public Question get(@PathVariable long Id){
        return questionService.getOne(Id);
    }
    //get question of specific
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionsOfQuiz(@PathVariable Long quizId){
        return questionService.getQuestionsQuiz(quizId);
    }
}
