package com.example.bioskop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.logging.SocketHandler;

public class MojeRezervacijeAdapter extends BaseAdapter {

    List<ItemMojeRezervacije> data;
    SharedPreferences prefRez;
    Context context;


    public MojeRezervacijeAdapter(List<ItemMojeRezervacije> data, Context context) {
        this.data = data;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.list_item_moje_rezervacije, parent, false);
        }

        TextView textViewFilm = convertView.findViewById(R.id.textViewMojeRezFilm);
        TextView textViewDatum = convertView.findViewById(R.id.textViewMojeRezDatum);
        TextView textViewVreme = convertView.findViewById(R.id.textViewMojeRezVreme);
        TextView textViewCena = convertView.findViewById(R.id.textViewMojeRezCena);
        TextView textViewBioskop = convertView.findViewById(R.id.textViewBioskop);
        Button buttonOtkazi = convertView.findViewById(R.id.buttonMojeRezOktazi);

        final int id = data.get(position).getId();
        prefRez = context.getSharedPreferences("rezervacija_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefRez.edit();
        editor.putInt("id", id);
        editor.commit();
        textViewFilm.setText(data.get(position).getFilm());
        textViewDatum.setText(data.get(position).getDatum());
        textViewVreme.setText(data.get(position).getVreme());
        double cena = data.get(position).getCena()*data.get(position).getBrojKarata();
        textViewCena.setText(Double.toString(cena));
        textViewBioskop.setText(data.get(position).getBioskop());

        buttonOtkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("id", id);
                    sendPostRequest2(jsonObject);
                    data.remove(position);
                    MojeRezervacijeAdapter.this.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        return convertView;
    }

    public void sendPostRequest2(final JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/rezervacija/deleteRezervacija";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public byte[] getBody() throws AuthFailureError {
                return jsonObject.toString().getBytes();
            }

            @Override
            public String getBodyContentType(){
                return "application/json";
            }
        };
        queue.add(stringRequest);
        Toast.makeText(context, "Uspešno otkazana rezervacija", Toast.LENGTH_LONG).show();
    }



}
