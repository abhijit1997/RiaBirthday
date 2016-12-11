package com.example.riabirthday;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Person {

    private static final String KEY_VIDEO_NAME = "videoName";
    private static final String KEY_PERSON_NAME = "name";
    private static final String KEY_QUESTIONS = "questions";

    private String mName;
    private List<Question> mQuestions;
    private boolean mVideoPlayed;
    private int mVideoResourceId;

    public Person (String name) {
        mName = name;
        mVideoResourceId = 0;
        mQuestions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        mQuestions.add(question);
    }

    public String getName() {
        return mName;
    }

    public Iterator<Question> questionsIterator() {
        return mQuestions.iterator();
    }

    public boolean hasVideo() {
        return (mVideoResourceId != 0) && !mVideoPlayed;
    }

    public void setVideoPlayed(boolean played) {
        mVideoPlayed = played;
    }

    public void setVideoResourceId(int resourceId) {
        mVideoResourceId = resourceId;
    }

    public int getVideoResourceId() {
        return mVideoResourceId;
    }

    public static Person buildPersonFromJson(JSONObject personJson, Context context) throws JSONException {
        Person person = new Person(personJson.getString(KEY_PERSON_NAME));
        JSONArray questionsArray = personJson.getJSONArray(KEY_QUESTIONS);
        for (int j = 0; j < questionsArray.length(); j++) {
            JSONObject questionJson = questionsArray.getJSONObject(j);
            Question question = buildQuestionFromJSON(questionJson, context);
            person.addQuestion(question);
        }

        if (personJson.has(KEY_VIDEO_NAME)) {
            String videoFileName = personJson.getString(KEY_VIDEO_NAME);
            int resourceId = context.getResources().getIdentifier(videoFileName, "raw", context.getPackageName());
            person.setVideoResourceId(resourceId);
        }

        return person;
    }

    private static Question buildQuestionFromJSON(JSONObject questionJson, Context context) throws JSONException {
        Question.Builder builder = new Question.Builder()
                .setQuestion(questionJson.getString("question"))
                .setOption1(questionJson.getString("option1"))
                .setOption2(questionJson.getString("option2"))
                .setOption3(questionJson.getString("option3"));

        if (questionJson.has("option4")) {
            builder.setOption4(questionJson.getString("option4"));
        }

        builder.setCorrectOption(stringToOption(questionJson.getString("correctOption")));

        if (questionJson.has("audioName")) {
            String audioFileName = questionJson.getString("audioName");
            int resourceId = context.getResources().getIdentifier(audioFileName, "raw", context.getPackageName());
            builder.setAudioResourceId(resourceId);
        }
        return builder.question();
    }

    private static @Question.Options int stringToOption(String option) {
        if (option == null || option.isEmpty()) {
            return Question.OPTION_INVALID;
        }

        if (option.equals("1")) {
            return Question.OPTION1;
        }

        if (option.equals("2")) {
            return Question.OPTION2;
        }

        if (option.equals("3")) {
            return Question.OPTION3;
        }

        if (option.equals("4")) {
            return Question.OPTION4;
        }

        return Question.OPTION_INVALID;
    }
}
