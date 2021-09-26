package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private String[]  Title;
    private String[] degree;
    private int[] imge;

    public CustomAdapter(Context context, String[] text1,int[] imageIds, String[] weather) {
        mContext = context;
        Title = text1;
        imge = imageIds;
        degree = weather;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Title.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View row;
        row = inflater.inflate(R.layout.row, parent, false);
        TextView title;
        ImageView i1;
        TextView weath;
        i1 = (ImageView) row.findViewById(R.id.imgIcon);
        title = (TextView) row.findViewById(R.id.txtTitle);
        weath = (TextView) row.findViewById(R.id.count);
        title.setText(Title[position]);
        i1.setImageResource(imge[position]);
        weath.setText(degree[position]+ (char) 0x00B0 );
        return (row);
    }
}
