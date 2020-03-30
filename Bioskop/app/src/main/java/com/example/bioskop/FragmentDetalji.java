package com.example.bioskop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telecom.Call;
import android.text.Layout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class FragmentDetalji extends Fragment {

    CallBackFragment callBackFragment;
    SharedPreferences pref2, pref;
    Button buttonRezervacija;
    TextView textViewInputNaslovFilma, textViewInputDuzinaTrajanja, textViewInputZanr, textViewInputDostupneVerzije, textViewInputOpis;
    ImageView imageViewFilm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalji_fragment, container, false);
        pref2 = getContext().getSharedPreferences("item_details", Context.MODE_PRIVATE);
        setHasOptionsMenu(true);
        textViewInputNaslovFilma = view.findViewById(R.id.textViewInputNaslovFilma);
        textViewInputDuzinaTrajanja = view.findViewById(R.id.textViewInputDuzinaTrajanja);
        textViewInputZanr = view.findViewById(R.id.textViewInputZanr);
        textViewInputDostupneVerzije = view.findViewById(R.id.textViewInputDostupneVerzije);
        textViewInputOpis = view.findViewById(R.id.textViewOpis);
        imageViewFilm = view.findViewById(R.id.imageView2);
        buttonRezervacija = view.findViewById(R.id.buttonRezervacija);

        buttonRezervacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBackFragment!=null){
                    callBackFragment.changeFragment();
                }
            }
        });

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("film", pref2.getString("film", null));
            sendPostRequest(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }

    public void sendPostRequest(JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/film/getMovieDetails";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String film = response.getString("nazivFilma");
                    int duzina = response.getInt("duzinaFilma");
                    String zanr = response.getString("zanrFilma");
                    String verzija = response.getString("verzijaFilma");
                    String opis = response.getString("opisFilma");
                    byte[] bytes = Base64.decode(response.getString("slika"), Base64.DEFAULT);
                    Bitmap slika = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    textViewInputNaslovFilma.setText(film);
                    textViewInputDuzinaTrajanja.setText(Integer.toString(duzina)+" min");
                    textViewInputZanr.setText(zanr);
                    textViewInputDostupneVerzije.setText(verzija);
                    textViewInputOpis.setText(opis);
                    imageViewFilm.setImageBitmap(slika);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
    }

    public void setCallBackFragment(CallBackFragment callBackFragment){
        this.callBackFragment = callBackFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profil:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentProfil());
                fragmentTransaction.commit();
                return true;
            case R.id.mojeRezervacije:
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentMojeRezervacije());
                fragmentTransaction.commit();
                return true;
            case R.id.odjava:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout(){
        pref = getContext().getSharedPreferences("user_details", MODE_PRIVATE);
        pref.edit().clear().commit();
        Intent switchIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(switchIntent);
    }
}
