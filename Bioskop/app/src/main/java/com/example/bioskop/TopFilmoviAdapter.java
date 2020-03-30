package com.example.bioskop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TopFilmoviAdapter extends BaseAdapter {

    List<ItemTopFilmovi> data;

    public TopFilmoviAdapter(List<ItemTopFilmovi> data) {
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
            convertView = inflater.inflate(R.layout.list_item_top_filmovi, parent, false);
        }
        ImageView imageViewFilm = convertView.findViewById(R.id.imageViewTopFilm);
        TextView textViewNazivFilma = convertView.findViewById(R.id.textViewNazivFilma);

        Bitmap bmp = BitmapFactory.decodeByteArray(data.get(position).getSlika(), 0, data.get(position).getSlika().length);
        imageViewFilm.setImageBitmap(bmp);
        textViewNazivFilma.setText(data.get(position).getNaziv());

        return convertView;
    }
}
