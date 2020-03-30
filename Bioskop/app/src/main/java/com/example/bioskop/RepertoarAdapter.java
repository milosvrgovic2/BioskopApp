package com.example.bioskop;

import android.animation.LayoutTransition;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class RepertoarAdapter extends BaseAdapter {

    private List<Item> data;

    public RepertoarAdapter(List<Item> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        TextView textViewFilm = (TextView) convertView.findViewById(R.id.textViewFilm);
        TextView textViewZanr = (TextView) convertView.findViewById(R.id.textViewZanr);
        TextView textViewDuzina = (TextView) convertView.findViewById(R.id.textViewDuzina);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageFilm);

        textViewFilm.setText(data.get(position).getFilm());
        textViewZanr.setText(data.get(position).getZanr()+" | ");
        textViewDuzina.setText(Integer.toString(data.get(position).getDuzina())+" min");
        Bitmap bmp = BitmapFactory.decodeByteArray(data.get(position).getSlika(), 0, data.get(position).getSlika().length);
        imageView.setImageBitmap(bmp);

        return convertView;
    }

}
