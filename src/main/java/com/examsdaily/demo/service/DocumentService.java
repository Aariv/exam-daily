/**
 * 
 */
package com.examsdaily.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.examsdaily.demo.entities.Question;

/**
 * @author zentere
 *
 */
public interface DocumentService {

	public String uploadDocument(MultipartFile file);
	
	public List<Question> getQuestions();
}
