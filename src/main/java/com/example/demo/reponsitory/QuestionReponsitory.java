package com.example.demo.reponsitory;

import com.example.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionReponsitory extends JpaRepository<Question,Long> {
}
