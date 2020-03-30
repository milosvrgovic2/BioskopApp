package com.example.bioskop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

public class FragmentTopFilmovi extends Fragment {
    ListView listViewTopFilmovi;
    SharedPreferences pref2, pref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_filmovi_fragment, container, false);
        setHasOptionsMenu(true);
        listViewTopFilmovi = view.findViewById(R.id.listViewTopFilmovi);

        sendPostRequest();

        listViewTopFilmovi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textViewNazivFilma = (TextView)view.findViewById(R.id.textViewNazivFilma);
                String nazivFilma = textViewNazivFilma.getText().toString();
                pref2 = getContext().getSharedPreferences("item_details", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref2.edit();
                editor.putString("film", nazivFilma);
                editor.commit();
                Intent intent = new Intent(getActivity(), DetaljiActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void sendPostRequest(){
        String url = "http://10.0.2.2:8080/Bioskop/film/getMovieByOcena";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    List<ItemTopFilmovi> data = new ArrayList<ItemTopFilmovi>();
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        byte[] slika = Base64.decode(jsonObject.getString("slika"), Base64.DEFAULT);
                        String nazivFilma = jsonObject.getString("naziv");
                        ItemTopFilmovi itemTopFilmovi = new ItemTopFilmovi(slika, nazivFilma);
                        data.add(itemTopFilmovi);
                    }
                    TopFilmoviAdapter topFilmoviAdapter = new TopFilmoviAdapter(data);
                    listViewTopFilmovi.setAdapter(topFilmoviAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
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
