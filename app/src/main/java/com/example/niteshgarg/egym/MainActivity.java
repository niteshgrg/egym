package com.example.niteshgarg.egym;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    private static final String SELECTED_KEY = "selected_position";
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    ListAdapter adapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private  ArrayList<com.example.niteshgarg.egym.model.results> results;

    public static boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        results = new ArrayList<com.example.niteshgarg.egym.model.results>();

        mListView = (ListView) findViewById(R.id.listview_users);

        adapter = new ListAdapter(getApplicationContext(), results);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {

                Uri gmmIntentUri = Uri.parse("geo:0,0?q="
                        + results.get(i).getUser().getLocation().getCity());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        adapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();

        if (isNetworkAvailable(getApplicationContext()) == true) {
            Toast.makeText(getApplicationContext(), "Loading Data", Toast.LENGTH_SHORT).show();
            getUser();
        } else {
            Toast.makeText(getApplicationContext(), "No internet connection available", Toast.LENGTH_LONG).show();
        }
    }

    private void getUser() {

        String API = "http://api.randomuser.me";
        Log.e(LOG_TAG, "hello");

        results.clear();

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API).build();
        UserInterface userApi = restAdapter.create(UserInterface.class);

        userApi.getUsers(100, new Callback<userPOJO>() {

            public void success(userPOJO userPOJO, Response response) {

                addUserList.get(getApplicationContext()).setResultsArrayList(userPOJO.getResults());
                results = addUserList.get(getApplicationContext()).getResultsArrayList();

                Log.e(LOG_TAG, "username: " + results.get(0).getUser().getUsername());

                adapter.updateContent(new ArrayList<com.example.niteshgarg.egym.model.results>(results));
                adapter.notifyDataSetChanged();
            }

            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
