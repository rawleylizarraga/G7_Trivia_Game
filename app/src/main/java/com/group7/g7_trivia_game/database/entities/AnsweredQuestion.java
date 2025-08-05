package com.group7.g7_trivia_game.database.entities;

import androidx.room.Entity;

import com.group7.g7_trivia_game.database.TriviaDatabase;

import java.util.Objects;

/**
 * description
 *
 * @author Madison Nolen
 * @since 8/5/2025
 */
@Entity(tableName = TriviaDatabase.ANSWERED_QUESTION_TABLE)
public class AnsweredQuestion {
    /**
     * Required fields: answeredQuestionId, userId, questionId, date answered, numberOfTries
     */
    private int answeredQuestionId;
    private int userId;
    private int questionId;
    private String dateAnswered;
    private int numberOfTries;

    /**
     * Constructor for AnsweredQuestion.
     * @param answeredQuestionId ID of the answered question
     * @param userId ID of the user who answered the question
     * @param questionId ID of the question that was answered
     * @param dateAnswered Date when the question was answered
     * @param numberOfTries Number of tries taken to answer the question
     */
    public AnsweredQuestion(int answeredQuestionId, int userId, int questionId, String dateAnswered, int numberOfTries) {
        this.answeredQuestionId = answeredQuestionId;
        this.userId = userId;
        this.questionId = questionId;
        this.dateAnswered = dateAnswered;
        this.numberOfTries = numberOfTries;
    }

    public int getAnsweredQuestionId() {
        return answeredQuestionId;
    }

    public void setAnsweredQuestionId(int answeredQuestionId) {
        this.answeredQuestionId = answeredQuestionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getDateAnswered() {
        return dateAnswered;
    }

    public void setDateAnswered(String dateAnswered) {
        this.dateAnswered = dateAnswered;
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public void setNumberOfTries(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AnsweredQuestion that = (AnsweredQuestion) o;
        return answeredQuestionId == that.answeredQuestionId && userId == that.userId && questionId == that.questionId && numberOfTries == that.numberOfTries && Objects.equals(dateAnswered, that.dateAnswered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answeredQuestionId, userId, questionId, dateAnswered, numberOfTries);
    }
}
