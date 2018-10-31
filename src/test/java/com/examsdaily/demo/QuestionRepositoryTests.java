package com.examsdaily.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.examsdaily.demo.entities.Answer;
import com.examsdaily.demo.entities.Question;
import com.examsdaily.demo.repositories.QuestionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuestionRepositoryTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	public void insertQuestion() {
		Question question = new Question();
		question.setQuestion("If the range of a function is a singleton set, then it is ");
		question.setQuestionNumber("Q.1)");
		question.setSuccess(true);

		Answer answer1 = new Answer();
		answer1.setAnswer("a constant function");
		answer1.setOption("a");
		answer1.setQuestion(question);

		Answer answer2 = new Answer();
		answer2.setAnswer("a constant function");
		answer2.setOption("a");
		answer2.setQuestion(question);

		Answer answer3 = new Answer();
		answer3.setAnswer("a constant function");
		answer3.setOption("a");
		answer3.setQuestion(question);

		Answer answer4 = new Answer();
		answer4.setAnswer("a constant function");
		answer4.setOption("a");
		answer4.setQuestion(question);

		question.getAnswers().add(answer1);
		question.getAnswers().add(answer2);
		question.getAnswers().add(answer3);
		question.getAnswers().add(answer4);

		questionRepository.save(question);

		Question dbQuestion = questionRepository.findById(question.getId()).get();

		assertNotNull(dbQuestion);
		assertNotNull(dbQuestion.getAnswers());

		List<Answer> answers = dbQuestion.getAnswers();

		assertTrue(answers.contains(answer1));
		assertTrue(answers.contains(answer2));
	}

}
