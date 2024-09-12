package com.example.demo.reponsitory;

import com.example.demo.entity.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTypeRepository extends JpaRepository<LessonType,Long> {
    LessonType findByTypeName(String typeName);
}
