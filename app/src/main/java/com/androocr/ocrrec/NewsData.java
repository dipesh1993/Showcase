package com.androocr.ocrrec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsData extends AppCompatActivity {
TextView title1,description;
ImageView imageView;
    String title,desc,img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_data);
        title1=findViewById(R.id.textView5);
        description=findViewById(R.id.textView6);
        imageView=findViewById(R.id.imageView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv = (TextView)findViewById(R.id.textofc);
        tv.setSelected(true);
        title = getIntent().getExtras().getString("name");
        desc = getIntent().getExtras().getString("desc");
        img = getIntent().getExtras().getString("info");

        title1.setText(title);
        description.setText(desc);
        Picasso.with(NewsData.this).load(img).into(imageView);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
