package com.example.demo.reponsitory;

import com.example.demo.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType,Long> {
    QuestionType findByTypeName(String typeName);
}
