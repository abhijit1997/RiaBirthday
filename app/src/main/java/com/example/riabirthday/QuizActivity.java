package com.example.riabirthday;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewQuestion;
    private Button mNext;
    private Button mOption1, mOption2, mOption3, mOption4;
    private Iterator<Person> mPersonIterator;
    private Iterator<Question> mQuestionIterator;
    private Question mCurrentQuestion;
    private Person mCurrentPerson;

    private MediaPlayer mMediaPlayer;

    private boolean isEndingVideoPlayed;
    private boolean isStartingVideoPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTextViewQuestion = (TextView) findViewById(R.id.question);
        mNext = (Button) findViewById(R.id.next);

        mNext.setOnClickListener(this);

        mOption1 = (Button) findViewById(R.id.option1);
        mOption2 = (Button) findViewById(R.id.option2);
        mOption3 = (Button) findViewById(R.id.option3);
        mOption4 = (Button) findViewById(R.id.option4);

        mOption1.setOnClickListener(this);
        mOption2.setOnClickListener(this);
        mOption3.setOnClickListener(this);
        mOption4.setOnClickListener(this);

        isEndingVideoPlayed = false;
        isStartingVideoPlayed = false;

        PeopleManager peopleManager = PeopleManager.getInstance(getApplicationContext());
        mPersonIterator = peopleManager.personIterator();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void setPerson(Person person) {
        mCurrentPerson = person;
        mQuestionIterator = person.questionsIterator();
        setQuestion(mQuestionIterator.next());
    }

    private void setQuestion(Question question) {
        mCurrentQuestion = question;
        mTextViewQuestion.setText(question.getQuestion());
        mOption1.setText(question.getOption1());
        mOption2.setText(question.getOption2());
        String option3 = question.getOption3();
        if (option3 == null || option3.isEmpty()) {
            mOption3.setVisibility(View.INVISIBLE);
            return;
        }
        mOption3.setText(option3);

        String option4 = question.getOption4();
        if (option4 == null || option4.isEmpty()) {
            mOption4.setVisibility(View.INVISIBLE);
            return;
        }
        mOption4.setText(option4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.option1:
                showToast(Question.OPTION1);
                break;

            case R.id.option2:
                showToast(Question.OPTION2);
                break;

            case R.id.option3:
                showToast(Question.OPTION3);
                break;

            case R.id.option4:
                showToast(Question.OPTION4);
                break;

            case R.id.next:
                next();
                break;
        }
    }

    private void showToast(@Question.Options int selectedOption) {
        String message;
        if (mCurrentQuestion.getCorrectOption() == selectedOption) {
            message = "Yay! Right answer";
        } else {
            message = "Wrong answer :(";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void next() {
        if (!isStartingVideoPlayed) {
            playStartingVideo();
            mNext.setText("Next");
            return;
        }

        if (mCurrentPerson == null) {
            setPerson(mPersonIterator.next());
            return;
        }

        if (mCurrentQuestion.hasAudio()) {
            playAudio();
            return;
        }

        if (mQuestionIterator.hasNext()) {
            setQuestion(mQuestionIterator.next());
            return;
        }

        if (mCurrentPerson.hasVideo()) {
            playPersonVideo();
            return;
        }

        if (mPersonIterator.hasNext()) {
            setPerson(mPersonIterator.next());
            return;
        }

        if (!isEndingVideoPlayed) {
            playEndingVideo();
            return;
        }

        finish();
    }

    private void playStartingVideo() {
        isStartingVideoPlayed = true;
        playVideo(R.raw.start_video);
    }

    private void playEndingVideo() {
        isEndingVideoPlayed = true;
        playVideo(R.raw.end_video);
    }

    private void playPersonVideo() {
        mCurrentPerson.setVideoPlayed(true);
        playVideo(mCurrentPerson.getVideoResourceId());
    }

    private void playVideo(int videoResourceId) {
        Intent intent = new Intent(this, VideoActivity.class);
        intent.putExtra(VideoActivity.INTENT_EXTRA_VIDEO_PATH, videoResourceId);
        startActivity(intent);
    }

    private void playAudio() {
        mMediaPlayer = MediaPlayer.create(this, mCurrentQuestion.geAudioResourceId());
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releaseMediaPlayer();
            }
        });
        mCurrentQuestion.setAudioPlayed(true);
        mNext.setEnabled(false);
        mMediaPlayer.start();
    }

    private void releaseMediaPlayer() {
        mNext.setEnabled(true);
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
