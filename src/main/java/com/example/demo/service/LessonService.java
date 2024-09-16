package com.example.demo.service;

import com.example.demo.MapStruct.LessonMapper;
import com.example.demo.dto.LessonDTO;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.LessonType;
import com.example.demo.reponsitory.LessonTypeRepository;
import com.example.demo.reponsitory.lessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {

    @Autowired
    private LessonTypeRepository lessonTypeRepository;

    @Autowired
    private lessonRepository lessonDAO;

    private final LessonMapper lessonMapper = LessonMapper.INSTANCE;

    public LessonDTO createLesson(LessonDTO lessonDTO)
    {
        Lesson lesson = lessonMapper.lessonDTOToLesson(lessonDTO);

        //lấy lessonType
        LessonType lessonType = lessonTypeRepository.findById(lessonDTO.getLessonTypeId())
                .orElseThrow(()-> new RuntimeException("LessonType not found"));

        lesson.setLessonType(lessonType);

        Lesson savedLesson = lessonDAO.save(lesson);

        return lessonMapper.lessonToLessonDTO(savedLesson);
    }

    public LessonDTO updateLesson(LessonDTO lessonDTO,Long id)
    {
        Lesson existingLesson = lessonDAO.findById(id)
                                        .orElseThrow(()-> new RuntimeException("Lesson not found"));

        existingLesson.setTitle(lessonDTO.getTitle());
        existingLesson.setContent(lessonDTO.getContent());
        existingLesson.setDuration(lessonDTO.getDuration());
        existingLesson.setNumberOfAttachments(lessonDTO.getNumberOfAttachments());


        //lấy LessonType
        LessonType lessonType = lessonTypeRepository.findById(lessonDTO.getLessonTypeId())
                .orElseThrow(()-> new RuntimeException("LessonType not found"));

        existingLesson.setLessonType(lessonType);

        Lesson updateLesson = lessonDAO.save(existingLesson);

        return lessonMapper.lessonToLessonDTO(updateLesson);

    }

    public void deleteLesson(Long id)
    {
        if (!lessonDAO.existsById(id))
        {
            throw new RuntimeException("Lesson not found");
        }

        lessonDAO.deleteById(id);
    }

}
