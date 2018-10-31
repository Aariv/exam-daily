package com.examsdaily.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.examsdaily.demo.entities.Answer;
import com.examsdaily.demo.entities.Question;
import com.examsdaily.demo.repositories.AnswerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AnswerRepositoryTests {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	public void insertAnswer() {
		Question question = new Question();
		question.setQuestion("If the range of a function is a singleton set, then it is ");
		question.setQuestionNumber("Q.1)");
		question.setSuccess(true);
		
		Answer answer1 = new Answer();
		answer1.setAnswer("a constant function");
		answer1.setOption("a");
		answer1.setQuestion(question);
		
		answerRepository.save(answer1);
		
		Answer dbAnswer = answerRepository.findById(answer1.getId()).get();
		
		assertNotNull(dbAnswer);
		assertNotNull(dbAnswer.getQuestion());
		assertEquals("If the range of a function is a singleton set, then it is ", dbAnswer.getQuestion().getQuestion());
	}
}
