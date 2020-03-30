package com.example.bioskop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentLogin extends Fragment {

    EditText etKorisnickoIme, etLozinka;
    Button buttonLogin, buttonRegister;
    SharedPreferences pref;
    CallBackFragment callBackFragment;
    private static final String TAG = "MyActivity";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        etKorisnickoIme = view.findViewById(R.id.etKorisnickoIme);
        etLozinka = view.findViewById(R.id.etLozinka);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonRegister = view.findViewById(R.id.buttonRegister);

       if(savedInstanceState!=null){
           String username = savedInstanceState.getString("username");
           etKorisnickoIme.setText(username);
           String password = savedInstanceState.getString("password");
           etLozinka.setText(password);
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("korisnickoIme", etKorisnickoIme.getText());
                    jsonObject.put("lozinka", etLozinka.getText());
                    if(NetConnection.isOnline(getActivity())){
                        sendPostRequest(jsonObject);

                    }
                    else{
                        Toast.makeText(getActivity(), "Ukljucite internet", Toast.LENGTH_LONG).show();
                    }
                }catch(JSONException e){

                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBackFragment!=null){
                    callBackFragment.changeFragment();
                }
            }
        });


        return view;
    }

    public void sendPostRequest(JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/auth/signin";
        pref = getContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SharedPreferences.Editor editor = pref.edit();
                try {
                    String ime = response.getString("ime");
                    String prezime = response.getString("prezime");
                    int id = response.getInt("id");
                    String korisnickoIme = response.getString("korisnickoIme");
                    String lozinka = response.getString("lozinka");
                    String email = response.getString("email");
                    editor.putString("ime", ime);
                    editor.putString("prezime", prezime);
                    editor.putInt("id", id);
                    editor.putString("korisnickoIme", korisnickoIme);
                    editor.putString("lozinka", lozinka);
                    editor.putString("email", email);
                    editor.commit();
                    Intent switchintent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(switchintent);
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               if(error.networkResponse.statusCode==401){
                    Toast toast = Toast.makeText(getActivity(),
                            "Pogrešno uneto korisničko ime ili lozinka", Toast.LENGTH_LONG);
                    toast.show();
                    etKorisnickoIme.getText().clear();
                    etLozinka.getText().clear();
                    etKorisnickoIme.requestFocus();
               }

            }
        });
        queue.add(jsonObjectRequest);
    }

    public void setCallBackFragment(CallBackFragment callBackFragment){
        this.callBackFragment = callBackFragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("username", etKorisnickoIme.getText().toString());
        outState.putString("password", etLozinka.getText().toString());
    }

/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null) {
            etKorisnickoIme.setText(savedInstanceState.getString("username"));
            etLozinka.setText(savedInstanceState.getString("password"));
        }
    }*/
}
