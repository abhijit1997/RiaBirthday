package com.example.riabirthday;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Question {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OPTION_INVALID, OPTION1, OPTION2, OPTION3, OPTION4})
    public @interface Options {}
    public static final int OPTION_INVALID = -1;
    public static final int OPTION1 = 0;
    public static final int OPTION2 = 1;
    public static final int OPTION3 = 2;
    public static final int OPTION4 = 3;

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private @Options int correctOption;

    private int audioResourceId;

    private boolean mAudioPlayed;

    private Question() {
        correctOption = OPTION_INVALID;
        audioResourceId = 0;
        setAudioPlayed(false);
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public @Options int getCorrectOption() {
        return correctOption;
    }

    public boolean hasAudio() {
        return (audioResourceId != 0) && !mAudioPlayed;
    }

    public int geAudioResourceId() {
        return audioResourceId;
    }

    public void setAudioPlayed(boolean played) {
        mAudioPlayed = played;
    }

    public static class Builder {
        private Question mQuestionObject;

        public Builder() {
            mQuestionObject = new Question();
        }

        public Builder setQuestion(String question) {
            mQuestionObject.question = question;
            return this;
        }

        public Builder setOption1(String option1) {
            mQuestionObject.option1 = option1;
            return this;
        }

        public Builder setOption2(String option2) {
            mQuestionObject.option2 = option2;
            return this;
        }

        public Builder setOption3(String option3) {
            mQuestionObject.option3 = option3;
            return this;
        }

        public Builder setOption4(String option4) {
            mQuestionObject.option4 = option4;
            return this;
        }

        public Builder setCorrectOption(@Options int correctOption) {
            mQuestionObject.correctOption = correctOption;
            return this;
        }

        public Builder setAudioResourceId(int audioResourceId) {
            mQuestionObject.audioResourceId = audioResourceId;
            return this;
        }

        public Question question() {
            return mQuestionObject;
        }
    }
}
