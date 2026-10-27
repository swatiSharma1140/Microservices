package com.quiz.services.impl;

import com.quiz.entities.Quiz;
import com.quiz.repositories.QuizRepository;
import com.quiz.services.QuestionClient;
import com.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
@Autowired
  private QuizRepository quizRepository;
  private QuestionClient questionClient;

    public QuizServiceImpl(QuestionClient questionClient) {
        this.questionClient = questionClient;
    }

    @Override
    public Quiz add(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> get() {
       List<Quiz> quizzes=quizRepository.findAll();

        List<Quiz> newQuizList = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
            newQuizList.add(quiz);
        }

        return newQuizList;
    }

    @Override
    public Quiz get(Long id) {
       Quiz quiz= quizRepository.findById(id).orElseThrow(()->new RuntimeException("Quiz not found"));
          quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
         return quiz;
    }
}
