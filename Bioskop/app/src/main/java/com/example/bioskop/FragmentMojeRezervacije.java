package com.example.bioskop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FragmentMojeRezervacije extends Fragment {
    ListView listViewMojeRezervacije;
    Button buttonOtkazi;
    SharedPreferences pref, prefRez;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.moje_rezervacije_fragment, container, false);
        listViewMojeRezervacije = view.findViewById(R.id.listViewMojeRez);
        setHasOptionsMenu(true);
        pref = getContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", pref.getInt("id", 0));
            sendPostRequest(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return view;
    }

    public void sendPostRequest(final JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/rezervacija/getRezervacijeKorisnika";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    List<ItemMojeRezervacije> data = new ArrayList<ItemMojeRezervacije>();
                    if(jsonArray.length()>0){
                        for(int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            int id = jsonObject1.getInt("id");
                            String film = jsonObject1.getString("film");
                            String datum = jsonObject1.getString("datum");
                            String vreme = jsonObject1.getString("vreme");
                            double cena = jsonObject1.getDouble("cena");
                            int brojKarata = jsonObject1.getInt("brojKarata");
                            String bioskop = jsonObject1.getString("bioskop");
                            ItemMojeRezervacije itemMojeRezervacije = new ItemMojeRezervacije(id, film, datum, vreme, cena, brojKarata, bioskop);
                            data.add(itemMojeRezervacije);
                        }
                        MojeRezervacijeAdapter mojeRezervacijeAdapter = new MojeRezervacijeAdapter(data, getContext());
                        listViewMojeRezervacije.setAdapter(mojeRezervacijeAdapter);
                    }
                    else{
                        Toast.makeText(getActivity(), "Nemate rezervacije", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
            /*case R.id.mojeRezervacije:
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentMojeRezervacije());
                fragmentTransaction.commit();
                return true;*/
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
