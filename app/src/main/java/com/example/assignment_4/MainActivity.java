package com.example.assignment_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView RV=findViewById(R.id.recycleView);
        String str;

        RV.setLayoutManager(new LinearLayoutManager(this));


        String [] title=new String[1];
        String [] level=new String[1];
        String [] books_description=new String[1];
        String [] Url=new String[1];
        String [] book_Cover=new String[1];

        try {

            InputStream is = getResources().openRawResource(R.raw.issues);
            byte[] booksData = new byte[is.available()];
            while (is.read(booksData)!=-1)
            {
            }

            str = new String(booksData , "UTF-8");
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.getString("books"));

            title=new String[jsonArray.length()];
            level=new String[jsonArray.length()];
            books_description=new String[jsonArray.length()];
            Url=new String[jsonArray.length()];
            book_Cover=new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                title[i]=obj.getString("title");
                level[i]=obj.getString("level");
                books_description[i]=obj.getString("info");
                Url[i]=obj.getString("url");
                book_Cover[i]=obj.getString("cover");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }



        MyAdapter Ad = new MyAdapter(this,title ,level, books_description, Url , book_Cover);
        RV.setAdapter(Ad);


    }
}
