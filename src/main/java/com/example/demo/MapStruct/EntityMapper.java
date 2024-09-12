package com.example.demo.MapStruct;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    // LessonType
    LessonTypeDTO lessonTypeToLessonTypeDTO(LessonType lessonType);
    LessonType lessonTypeDTOToLessonType(LessonTypeDTO lessonTypeDTO);

    // Quiz
    QuizDTO quizToQuizDTO(Quiz quiz);
    Quiz quizDTOToQuiz(QuizDTO quizDTO);

    // Question
    QuestionDTO questionToQuestionDTO(Question question);
    Question questionDTOToQuestion(QuestionDTO questionDTO);

    // QuestionType
    QuestionTypeDTO questionTypeToQuestionTypeDTO(QuestionType questionType);
    QuestionType questionTypeDTOToQuestionType(QuestionTypeDTO questionTypeDTO);

    // Option
    OptionDTO optionToOptionDTO(Option option);
    Option optionDTOToOption(OptionDTO optionDTO);

    // Map Comment entity to CommentDTO
    @Mapping(source = "lesson.id", target = "lessonId") // Map lesson ID to lessonId
    @Mapping(source = "user.id", target = "userId") // Map user ID to userId
    CommentDTO commentToCommentDTO(Comment comment);

    // Map CommentDTO to Comment entity
    @Mapping(source = "lessonId", target = "lesson.id") // Map lessonId to lesson
    @Mapping(source = "userId", target = "user.id") // Map userId to user
    Comment commentDTOToComment(CommentDTO commentDTO);

    List<CommentDTO> commentListToCommentDTOList(List<Comment> comments);
    List<Comment> commentDTOListToCommentList(List<CommentDTO> commentDTOs);

    // Session
    SessionDTO sessionToSessionDTO(Session session);
    Session sessionDTOToSession(SessionDTO sessionDTO);

    List<SessionDTO> sessionListToSessionDTOList(List<Session> sessions);
    List<Session> sessionDTOListToSessionList(List<SessionDTO> sessionDTOs);

    // Lesson mapping
    @Mapping(source = "lessonType", target = "lessonType")
    @Mapping(source = "comments", target = "comments", qualifiedByName = "commentsToCommentDTOList")
    @Mapping(source = "quizzes", target = "quizzes", qualifiedByName = "quizzesToQuizDTOList")
    @Mapping(source = "sessions", target = "sessions", qualifiedByName = "sessionsToSessionDTOList")
    LessonDTO lessonToLessonDTO(Lesson lesson);

    @Mapping(source = "lessonType", target = "lessonType")
    @Mapping(source = "comments", target = "comments", qualifiedByName = "commentDTOListToComments")
    @Mapping(source = "quizzes", target = "quizzes", qualifiedByName = "quizDTOListToQuizzes")
    @Mapping(source = "sessions", target = "sessions", qualifiedByName = "sessionDTOListToSessions")
    Lesson lessonDTOToLesson(LessonDTO lessonDTO);



    // Custom mapping for lists
    @Named("commentsToCommentDTOList")
    default List<CommentDTO> commentsToCommentDTOList(List<Comment> comments) {
        return comments.stream()
                .map(this::commentToCommentDTO)
                .collect(Collectors.toList());
    }

    @Named("commentDTOListToComments")
    default List<Comment> commentDTOListToComments(List<CommentDTO> commentDTOs) {
        return commentDTOs.stream()
                .map(this::commentDTOToComment)
                .collect(Collectors.toList());
    }

    @Named("quizzesToQuizDTOList")
    default List<QuizDTO> quizzesToQuizDTOList(List<Quiz> quizzes) {
        return quizzes.stream()
                .map(this::quizToQuizDTO)
                .collect(Collectors.toList());
    }

    @Named("quizDTOListToQuizzes")
    default List<Quiz> quizDTOListToQuizzes(List<QuizDTO> quizDTOs) {
        return quizDTOs.stream()
                .map(this::quizDTOToQuiz)
                .collect(Collectors.toList());
    }

    @Named("sessionsToSessionDTOList")
    default List<SessionDTO> sessionsToSessionDTOList(List<Session> sessions) {
        return sessions.stream()
                .map(this::sessionToSessionDTO)
                .collect(Collectors.toList());
    }

    @Named("sessionDTOListToSessions")
    default List<Session> sessionDTOListToSessions(List<SessionDTO> sessionDTOs) {
        return sessionDTOs.stream()
                .map(this::sessionDTOToSession)
                .collect(Collectors.toList());
    }
}
