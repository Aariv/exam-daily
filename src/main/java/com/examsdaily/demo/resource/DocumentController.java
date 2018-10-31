package com.examsdaily.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.examsdaily.demo.entities.Question;
import com.examsdaily.demo.service.DocumentService;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@PostMapping("/uploadDocxFile")
	public String uploadDocxFile(@RequestParam("file") MultipartFile file) {
		documentService.uploadDocument(file);
		return "Success";
	}

	@GetMapping("/getQuestions")
	public List<Question> getQuestions() {
		List<Question> questions = documentService.getQuestions();
		return questions;
	}

}