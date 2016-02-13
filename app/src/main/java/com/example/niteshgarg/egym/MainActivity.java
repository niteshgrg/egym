package com.example.niteshgarg.egym;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.niteshgarg.egym.API.UserInterface;
import com.example.niteshgarg.egym.model.addUserList;
import com.example.niteshgarg.egym.model.userPOJO;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    ListAdapter adapter;

    private  ArrayList<com.example.niteshgarg.egym.model.results> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = (ListView) findViewById(R.id.listview_users);

        adapter = new ListAdapter(getApplicationContext(), results);
        getUser();
        mListView.setAdapter(adapter);

        setContentView(R.layout.activity_main);
    }


    private void getUser() {

        String API = "http://api.randomuser.me";

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();
        UserInterface userApi = restAdapter.create(UserInterface.class);

        userApi.getUsers(100, new Callback<userPOJO>() {

            public void success(userPOJO userPOJO, Response response) {

                addUserList.get(getApplicationContext()).setResultsArrayList(userPOJO.getResults());
                results = addUserList.get(getApplicationContext()).getResultsArrayList();

                //Log.e(LOG_TAG, "username: " + results.get(0).getUser().getUsername());

                adapter.updateContent(new ArrayList<com.example.niteshgarg.egym.model.results>(results));
                adapter.notifyDataSetChanged();
            }

            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
