package com.example.riabirthday;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PeopleManager {

    private static final String TAG = "PeopleManager";
    private static PeopleManager sInstance;
    private List<Person> mPersons;

    private Context mContext;

    private PeopleManager(Context context) {
        mPersons = new ArrayList<>();
        mContext = context;
        populatePeople();
    }

    public static PeopleManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PeopleManager(context);
        }

        return sInstance;
    }

    private void populatePeople() {
        String jsonString = getJSONFromFile();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray peopleArray = jsonObject.getJSONArray("people");
            for (int i = 0; i < peopleArray.length(); i++) {
                JSONObject personJson = peopleArray.getJSONObject(i);
                Person person = Person.buildPersonFromJson(personJson, mContext);
                mPersons.add(person);
            }
        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public Iterator<Person> personIterator() {
        return mPersons.iterator();
    }

    private String getJSONFromFile() {
        try {
            InputStream inputStream = mContext.getResources()
                    .openRawResource(mContext.getResources()
                            .getIdentifier("data", "raw", mContext.getPackageName()));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                total.append(line);
            }

            return total.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
