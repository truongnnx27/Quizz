package com.example.demo.reponsitory;

import com.example.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionReponsitory extends JpaRepository<Question,Long> {
    List<Question> findByQuizId(Long qizId);
}
