/**
 * 
 */
package com.examsdaily.demo.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.examsdaily.demo.entities.Answer;
import com.examsdaily.demo.entities.Question;
import com.examsdaily.demo.repositories.QuestionRepository;
import com.examsdaily.demo.service.DocumentService;
import com.examsdaily.demo.utils.DexterUtils;

/**
 * @author zentere
 *
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public String uploadDocument(MultipartFile file) {
		try {
			readDocFile(convert(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
	
	public List<Question> readDocFile(File file) {
		try {
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);

			List<XWPFParagraph> paragraphs = document.getParagraphs();
			List<Question> questionBanks = new ArrayList<>();
			StringBuilder question = new StringBuilder();
			List<Answer> options = new ArrayList<Answer>();
			for (XWPFParagraph para : paragraphs) {
				String text = para.getText();
				if (text.startsWith("Q")) {
					question.append(text);
				} else if (text.contains("*")) {
					// answer
					Answer option = new Answer();
					String[] output =  DexterUtils.onStringSplit(text.trim());
					String optionString = output[0];
					String answer = output[1];
					
					option.setOption(optionString.trim());
					option.setAnswer(answer.trim());
					options.add(option);
				} else {
					// option
					Answer option = new Answer();
					String[] output =  DexterUtils.onStringSplit(text.trim());
					String optionString = output[0];
					String answer = output[1];
					
					option.setOption(optionString.trim());
					option.setAnswer(answer.trim());
					options.add(option);
				}
				if (text.startsWith("d")) {
					// add a exam object
					Question questionBank = new Question();
					String[] questions = DexterUtils.onStringSplit(question.toString());
					String questionNumber = questions[0];
					String questionString = questions[1];
					questionBank.setQuestionNumber(questionNumber);
					questionBank.setQuestion(questionString);
					
					questionBank.getAnswers().addAll(options);
					
					questionBank.getAnswers().forEach(answer -> {
						answer.setQuestion(questionBank);
					});
					
					questionBank.setSuccess(Boolean.TRUE);
					
					questionRepository.save(questionBank);
					
					questionBanks.add(questionBank);
					
					options = new ArrayList<Answer>();
					question = new StringBuilder();
				}
			}
			return questionBanks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Question> getQuestions() {
		List<Question> target = new ArrayList<>();
		questionRepository.findAll().forEach(target::add);
		return target;
	}

}
