package com.examsdaily.demo.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * @author zentere
 *
 */
@Entity
public class Question {

	@Id
	@GeneratedValue
	private Integer id;
	private String questionNumber;
	private String question;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Answer> answers = new ArrayList<Answer>();

	private Boolean success = Boolean.FALSE;

	public Question() {
		answers = new ArrayList<Answer>();
	}

	/**
	 * @param questionNumber
	 * @param question
	 */
	public Question(String questionNumber, String question) {
		super();
		this.questionNumber = questionNumber;
		this.question = question;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the questionNumber
	 */
	public String getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * @param questionNumber
	 *            the questionNumber to set
	 */
	public void setQuestionNumber(String questionNumber) {new ArrayList<Answer>();
		this.questionNumber = questionNumber;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
