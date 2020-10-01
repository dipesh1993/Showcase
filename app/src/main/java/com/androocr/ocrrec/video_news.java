package com.androocr.ocrrec;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.AsyncTask;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class video_news extends Fragment {

    ListView listView;
    String[] heroes;
    ProgressBar progressBar;

    ArrayList<DataModelNews> dataModels;
    private static CustomVideosAdapter adapter;

    public video_news() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_text_news, container, false);
        listView = (ListView) v.findViewById(R.id.listView);
        progressBar=v.findViewById(R.id.pbar);
        dataModels= new ArrayList<>();

        getJSON("http://192.168.2.8/news_theme/video.php");

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                // Toast.makeText(v.gegetApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    progressBar.setVisibility(View.GONE);
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        if (json != null)
        {
            JSONArray jsonArray = new JSONArray(json);
            heroes = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                // heroes[i] = obj.getString("title_2");
                dataModels.add(new DataModelNews(obj.getString("title_1"), obj.getString("description_3"), obj.getString("link_1")));
            }
            adapter = new CustomVideosAdapter(dataModels, getContext());
            listView.setAdapter(adapter);

            //    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.news_single_layout, heroes);
            //  listView.setAdapter(arrayAdapter);
        }
    }
}




