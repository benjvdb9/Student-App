package com.example.benjvdb.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    //private RecyclerView resultview;
    //private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new QueryTask().execute();
    }

    public class QueryTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL url_object = new URL("https://andfun-weather.udacity.com/weather");
                HttpURLConnection urlConnection = (HttpURLConnection) url_object.openConnection();

                try {
                    InputStream in = urlConnection.getInputStream();

                    Scanner scanner = new Scanner(in);
                    scanner.useDelimiter("\\A");

                    boolean hasInput = scanner.hasNext();

                    if (hasInput) {
                        return scanner.next();
                    } else {
                        return null;
                    }
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception ex) {
                Log.e("App", "Error", ex);
                return null;
            }
        }

        @Override
        protected void onPostExecute(String queryResults) {
            String querytest = "Results: " + queryResults;
            Log.v("QTask", querytest);

            TextView name = (TextView)findViewById(R.id.text2);
            name.setText(queryResults);
        }
    }
}
