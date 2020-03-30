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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class FragmentProfil extends Fragment {

    TextView textViewProfilKorisnickoIme, textViewProfilIme, textViewProfilPrezime, textViewProfilEmail;
    Button buttonSacuvajPromene;
    EditText editTextStaraLozinka, editTextNovaLozinka, editTextPonovljenaLozinka;
    SharedPreferences pref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil_fragment, container, false);
        setHasOptionsMenu(true);
        textViewProfilKorisnickoIme = view.findViewById(R.id.textViewProfilKorisnickoIme);
        textViewProfilIme = view.findViewById(R.id.textViewProfilIme);
        textViewProfilPrezime = view.findViewById(R.id.textViewProfilPrezime);
        textViewProfilEmail = view.findViewById(R.id.textViewProfilEmail);
        editTextStaraLozinka = view.findViewById(R.id.editTextStaraLozinka);
        editTextNovaLozinka = view.findViewById(R.id.editTextNovaLozinka);
        editTextPonovljenaLozinka = view.findViewById(R.id.editTextPonoviLozinku);
        buttonSacuvajPromene = view.findViewById(R.id.buttonSacuvajPromene);

        pref = getContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
        textViewProfilKorisnickoIme.setText(pref.getString("korisnickoIme", null));
        textViewProfilIme.setText(pref.getString("ime", null));
        textViewProfilPrezime.setText(pref.getString("prezime", null));
        textViewProfilEmail.setText(pref.getString("email", null));


        buttonSacuvajPromene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextStaraLozinka.getText().toString().equals(pref.getString("lozinka", null)) && editTextNovaLozinka.getText().toString().equals(editTextPonovljenaLozinka.getText().toString())) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("id", pref.getInt("id", 0));
                        jsonObject.put("lozinka", editTextNovaLozinka.getText().toString());
                        sendPostRequest(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(!editTextStaraLozinka.getText().toString().equals(pref.getString("lozinka", null))){
                    Toast.makeText(getActivity(), "Lozinka nije ispravna", Toast.LENGTH_LONG).show();
                    editTextStaraLozinka.getText().clear();
                    editTextStaraLozinka.requestFocus();

                }
                else if(!editTextNovaLozinka.getText().toString().equals(editTextPonovljenaLozinka.getText().toString())){
                    Toast.makeText(getActivity(), "Lozinke nisu iste", Toast.LENGTH_LONG).show();
                    editTextNovaLozinka.getText().clear();
                    editTextPonovljenaLozinka.getText().clear();
                    editTextNovaLozinka.requestFocus();
                }
            }

        });

        return view;
    }

    public void sendPostRequest(JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/auth/updatePassword";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
        Context context = getContext();
        CharSequence text = "Uspešno ste promenili lozinku";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
            /*case R.id.profil:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentProfil());
                fragmentTransaction.commit();
                return true;*/
            case R.id.mojeRezervacije:
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
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
