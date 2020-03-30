package com.example.bioskop;

import android.content.Context;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FragmentRegister extends Fragment {

    EditText etIme, etPrezime, etKorsnickoIme, etLoznika, etEmail;
    Button btRegister;
    private static final String TAG = "FragmentRegister";
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        etIme = view.findViewById(R.id.etIme);
        etPrezime = view.findViewById(R.id.etPrezime);
        etKorsnickoIme = view.findViewById(R.id.etKorisnickoIme);
        etLoznika = view.findViewById(R.id.etLozinka);
        etEmail = view.findViewById(R.id.etEmail);
        btRegister = view.findViewById(R.id.buttonRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("ime", etIme.getText());
                    jsonObject.put("prezime", etPrezime.getText());
                    jsonObject.put("email", etEmail.getText());
                    jsonObject.put("korisnickoIme", etKorsnickoIme.getText());
                    jsonObject.put("lozinka", etLoznika.getText());
                    sendPostRequest(jsonObject);
                }catch(JSONException e){

                }
            }
        });


        return view;
    }

    public void sendPostRequest(JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/auth/register";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        });
        queue.add(jsonObjectRequest);
        Toast.makeText(getActivity(), "Uspešno ste se registrovali", Toast.LENGTH_LONG).show();
    }

}
