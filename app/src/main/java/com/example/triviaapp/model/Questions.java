package com.example.triviaapp.model;

public class Questions {
    private String Answer;
    private boolean AnswerTrue;

    public Questions(){

    }

    public Questions(String answer, boolean answerTrue) {
        Answer = answer;
        AnswerTrue = answerTrue;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public boolean isAnswerTrue() {
        return AnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        AnswerTrue = answerTrue;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "Answer='" + Answer + '\'' +
                ", AnswerTrue=" + AnswerTrue +
                '}';
    }
}
