package com.androocr.ocrrec;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CustomVideosAdapter extends ArrayAdapter<DataModelNews> implements View.OnClickListener{

    private ArrayList<DataModelNews> dataSet;
    Context mContext;
    String img_url,img_url1;
    String videoId,videoId1;
    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType,info1;
        ImageView info;

    }

    public CustomVideosAdapter(ArrayList<DataModelNews> data, Context context) {
        super(context, R.layout.fragment_video_news, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModelNews dataModel=(DataModelNews)object;

        switch (v.getId())
        {

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final DataModelNews dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_video_news, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.txtType = (TextView) convertView.findViewById(R.id.textView2);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.info1 =  convertView.findViewById(R.id.textView4);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        convertView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    videoId1=extractYoutubeId(dataModel.getFeature());
                    img_url1="http://img.youtube.com/vi/"+videoId1+"/0.jpg";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(mContext,VideoData.class);
                intent.putExtra("name", viewHolder.txtName.getText().toString());
                intent.putExtra("desc", viewHolder.txtType.getText().toString());
                intent.putExtra("info", videoId1);
                intent.putExtra("info1",img_url1);

                mContext.startActivity(intent);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;
        try {
            videoId=extractYoutubeId(dataModel.getFeature());
            Log.e("VideoId is->","" + videoId);

            img_url="http://img.youtube.com/vi/"+videoId+"/0.jpg";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtType.setText(dataModel.getVersion_number());
        viewHolder.info1.setText(dataModel.getFeature());
        viewHolder.info1.setVisibility(View.GONE);
        Picasso.with(getContext()).load(img_url).into(viewHolder.info);
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }

    public String extractYoutubeId(String url) throws MalformedURLException {
        String query = new URL(url).getQuery();
        String[] param = query.split("&");
        String id = null;
        for (String row : param) {
            String[] param1 = row.split("=");
            if (param1[0].equals("v")) {
                id = param1[1];
            }
        }
        return id;
    }
}
