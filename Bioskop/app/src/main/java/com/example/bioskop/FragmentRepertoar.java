package com.example.bioskop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class FragmentRepertoar extends Fragment {

    Spinner spinnerBioskop, spinnerTehnologije, spinnerVerzije, spinnerDatumi;
    Button buttonPretrazi;
    ListView listView;

    ArrayList<String> lokacije = new ArrayList<String>();
    ArrayList<String> tehnologije = new ArrayList<String>();
    ArrayList<String> verzije = new ArrayList<String>();
    ArrayList<String> datumi = new ArrayList<String>();

    String urlBioskop = "http://10.0.2.2:8080/Bioskop/projekcija/getAllAddress";
    String urlTehnologije = "http://10.0.2.2:8080/Bioskop/film/getAllTechnologies";
    String urlVerzije = "http://10.0.2.2:8080/Bioskop/film/getAllVersions";

    String izabranaLokacija, izabranaTehnologija, izabranaVerzija, izabranDatum;

    final JSONObject jsonObject = new JSONObject();

    SharedPreferences pref2, pref;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repertoar_fragment, container, false);
        setHasOptionsMenu(true);
        listView = view.findViewById(R.id.listView);
        spinnerBioskop = view.findViewById(R.id.spinnerBioskop);
        spinnerTehnologije = view.findViewById(R.id.spinnerTehnologija);
        spinnerVerzije = view.findViewById(R.id.spinnerVerzija);
        spinnerDatumi = view.findViewById(R.id.spinnerDatum);
        buttonPretrazi = view.findViewById(R.id.buttonPretrazi);

        loadSpinnerBioskopData(urlBioskop);
        loadSpinnerTehnologijeData(urlTehnologije);
        loadSpinnerVerzijeData(urlVerzije);
        loadSpinnerDatumiData();

        spinnerBioskop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                izabranaLokacija = spinnerBioskop.getItemAtPosition(spinnerBioskop.getSelectedItemPosition()).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTehnologije.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                izabranaTehnologija = spinnerTehnologije.getItemAtPosition(spinnerTehnologije.getSelectedItemPosition()).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerVerzije.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                izabranaVerzija = spinnerVerzije.getItemAtPosition(spinnerVerzije.getSelectedItemPosition()).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerDatumi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                izabranDatum = spinnerDatumi.getItemAtPosition(spinnerDatumi.getSelectedItemPosition()).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonPretrazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    jsonObject.put("adresaBioskopa", izabranaLokacija);
                    jsonObject.put("datum", izabranDatum);
                    jsonObject.put("tehnologija", izabranaTehnologija);
                    jsonObject.put("verzija", izabranaVerzija);
                    sendPostRequest(jsonObject);
                }catch (JSONException e){

                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView film = (TextView)view.findViewById(R.id.textViewFilm);
                String nazivFilma = film.getText().toString();
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

    private void loadSpinnerBioskopData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String lokacija=jsonObject1.getString("lokacija");
                        lokacije.add(lokacija);
                    }

                    spinnerBioskop.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, lokacije));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void loadSpinnerTehnologijeData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String tehnologija=jsonObject1.getString("tehnologija");
                        tehnologije.add(tehnologija);
                    }

                   spinnerTehnologije.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tehnologije));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }

    private void loadSpinnerVerzijeData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        String verzija=jsonObject1.getString("verzija");
                        verzije.add(verzija);
                    }

                    spinnerVerzije.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, verzije));


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadSpinnerDatumiData(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        LocalDate date = LocalDate.now();
        String dateString = date.format(formatter);
        LocalDate date2 = date.plusDays(1);
        String date2String = date2.format(formatter);
        LocalDate date3 = date2.plusDays(1);
        String date3String = date3.format(formatter);
        LocalDate date4 = date3.plusDays(1);
        String date4String = date4.format(formatter);
        LocalDate date5 = date4.plusDays(1);
        String date5String = date5.format(formatter);
        LocalDate date6 = date5.plusDays(1);
        String date6String = date6.format(formatter);

        datumi.add(dateString);
        datumi.add(date2String);
        datumi.add(date3String);
        datumi.add(date4String);
        datumi.add(date5String);
        datumi.add(date6String);

        spinnerDatumi.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, datumi));

    }

   public void sendPostRequest(final JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/film/getAllMovies";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    List<Item> data = new ArrayList<Item>();
                    if(jsonArray.length()>0) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String film = jsonObject1.getString("nazivFilma");
                            String zanr = jsonObject1.getString("nazivZanra");
                            int duzina = jsonObject1.getInt("duzinaFilma");
                            byte[] slika = Base64.decode(jsonObject1.getString("slika"), Base64.DEFAULT);
                            //Bitmap slika = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            Item item = new Item(film, zanr, duzina, slika);
                            data.add(item);
                        }
                        RepertoarAdapter adapter = new RepertoarAdapter(data);
                        listView.setAdapter(adapter);
                    }
                    else{
                        Toast.makeText(getActivity(), "Nema filmova za izabrane parametre", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError{
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
