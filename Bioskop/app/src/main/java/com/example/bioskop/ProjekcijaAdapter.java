package com.example.bioskop;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ProjekcijaAdapter extends RecyclerView.Adapter<ProjekcijaAdapter.MyViewHolder> {
    int selectedPosition = -1;
    private final List<ItemProjekcija> data;
    Context context;
    SharedPreferences pref3;

    public ProjekcijaAdapter(List<ItemProjekcija> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_projekcija, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final ItemProjekcija itemProjekcija = data.get(position);
        holder.vreme.setText(itemProjekcija.getVreme());
        holder.sala.setText(itemProjekcija.getSala());

        if(selectedPosition==position){
            holder.itemView.setBackgroundColor(Color.parseColor("#e0e0d1"));
        }
        else{
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.item_projekcija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition=position;
                notifyDataSetChanged();
                pref3=context.getSharedPreferences("item_projekcija", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorPref3 = pref3.edit();
                editorPref3.putInt("idProjekcija", itemProjekcija.getId());
                editorPref3.commit();
                //Toast.makeText(context, "Id projekcije = "+pref3.getInt("idProjekcija", 0), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout item_projekcija;
        TextView vreme, sala, id;
        MyViewHolder(View view){
            super(view);
            item_projekcija = view.findViewById(R.id.list_item_projekcija);
            vreme = view.findViewById(R.id.textViewVreme);
            sala = view.findViewById(R.id.textViewSala);

        }

    }


}
