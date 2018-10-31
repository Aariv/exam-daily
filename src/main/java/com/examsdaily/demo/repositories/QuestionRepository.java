/**
 * 
 */
package com.examsdaily.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.examsdaily.demo.entities.Question;

/**
 * @author zentere
 *
 */
public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
