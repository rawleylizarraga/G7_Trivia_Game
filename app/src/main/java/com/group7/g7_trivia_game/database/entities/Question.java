package com.group7.g7_trivia_game.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.group7.g7_trivia_game.database.TriviaDatabase;

import java.util.Locale;
import java.util.Objects;

/**
 * description
 *
 * @author
 * @since 7/31/2025
 */

@Entity(tableName = TriviaDatabase.QUESTION_TABLE)
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int questionId;
    private int points;
    private String answerCorrect;
    private String answerWrong1;
    private String answerWrong2;
    private String answerWrong3;
    private String category;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionId == question.questionId && points == question.points && Objects.equals(answerCorrect, question.answerCorrect) && Objects.equals(answerWrong1, question.answerWrong1) && Objects.equals(answerWrong2, question.answerWrong2) && Objects.equals(answerWrong3, question.answerWrong3) && Objects.equals(category, question.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, points, answerCorrect, answerWrong1, answerWrong2, answerWrong3, category);
    }

    @NonNull
    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", points=" + points +
                ", answerCorrect='" + answerCorrect + '\'' +
                ", answerWrong1='" + answerWrong1 + '\'' +
                ", answerWrong2='" + answerWrong2 + '\'' +
                ", answerWrong3='" + answerWrong3 + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    //Getters and Setters
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(String answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public String getAnswerWrong1() {
        return answerWrong1;
    }

    public void setAnswerWrong1(String answerWrong1) {
        this.answerWrong1 = answerWrong1;
    }

    public String getAnswerWrong2() {
        return answerWrong2;
    }

    public void setAnswerWrong2(String answerWrong2) {
        this.answerWrong2 = answerWrong2;
    }

    public String getAnswerWrong3() {
        return answerWrong3;
    }

    public void setAnswerWrong3(String answerWrong3) {
        this.answerWrong3 = answerWrong3;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
