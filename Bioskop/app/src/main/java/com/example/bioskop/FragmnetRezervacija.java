package com.example.bioskop;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FragmnetRezervacija extends Fragment {

    RecyclerView recyclerView;

    final JSONObject jsonObject = new JSONObject();

    SharedPreferences pref, pref2, pref4, pref3;

    Button buttonA1, buttonB1, buttonC1, buttonD1, buttonE1, buttonF1, buttonA2, buttonB2, buttonC2, buttonD2, buttonE2, buttonF2,
    buttonA3, buttonB3, buttonC3, buttonD3, buttonE3, buttonF3, buttonA4, buttonB4, buttonC4, buttonD4, buttonE4, buttonF4,
    buttonA5, buttonB5, buttonC5, buttonD5, buttonE5, buttonF5, buttonRez;

    List<String> izabranaSedista = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rezervacija_fragment, container, false);
        buttonA1 = view.findViewById(R.id.buttonA1);
        buttonB1 = view.findViewById(R.id.buttonB1);
        buttonC1 = view.findViewById(R.id.buttonC1);
        buttonD1 = view.findViewById(R.id.buttonD1);
        buttonE1 = view.findViewById(R.id.buttonE1);
        buttonF1 = view.findViewById(R.id.buttonF1);
        buttonA2 = view.findViewById(R.id.buttonA2);
        buttonB2 = view.findViewById(R.id.buttonB2);
        buttonC2 = view.findViewById(R.id.buttonC2);
        buttonD2 = view.findViewById(R.id.buttonD2);
        buttonE2 = view.findViewById(R.id.buttonE2);
        buttonF2 = view.findViewById(R.id.buttonF2);
        buttonA3 = view.findViewById(R.id.buttonA3);
        buttonB3 = view.findViewById(R.id.buttonB3);
        buttonC3 = view.findViewById(R.id.buttonC3);
        buttonD3 = view.findViewById(R.id.buttonD3);
        buttonE3 = view.findViewById(R.id.buttonE3);
        buttonF3 = view.findViewById(R.id.buttonF3);
        buttonA4 = view.findViewById(R.id.buttonA4);
        buttonB4 = view.findViewById(R.id.buttonB4);
        buttonC4 = view.findViewById(R.id.buttonC4);
        buttonD4 = view.findViewById(R.id.buttonD4);
        buttonE4 = view.findViewById(R.id.buttonE4);
        buttonF4 = view.findViewById(R.id.buttonF4);
        buttonA5 = view.findViewById(R.id.buttonA5);
        buttonB5 = view.findViewById(R.id.buttonB5);
        buttonC5 = view.findViewById(R.id.buttonC5);
        buttonD5 = view.findViewById(R.id.buttonD5);
        buttonE5 = view.findViewById(R.id.buttonE5);
        buttonF5 = view.findViewById(R.id.buttonF5);
        buttonRez = view.findViewById(R.id.buttonRez);
        recyclerView = view.findViewById(R.id.recyclerViewProjekcije);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        setHasOptionsMenu(true);

        pref2 = getContext().getSharedPreferences("item_details", Context.MODE_PRIVATE);
        try {
            jsonObject.put("film", pref2.getString("film", null));
            sendPostRequest(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pref4 = getContext().getSharedPreferences("selected_seat", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorPref4 = pref4.edit();

        buttonA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonA1.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteA1 = buttonA1.getText().toString();
                    izabranaSedista.add(sedisteA1);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteA1 = buttonA1.getText().toString();
                    izabranaSedista.remove(sedisteA1);
                }
                else {
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonA2.getBackground();
                int color = buttonColor.getColor();

                if(color == Color.parseColor("#AAAAAA")) {
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteA2 = buttonA2.getText().toString();
                    izabranaSedista.add(sedisteA2);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteA2 = buttonA2.getText().toString();
                    izabranaSedista.remove(sedisteA2);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonA3.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteA3 = buttonA3.getText().toString();
                    izabranaSedista.add(sedisteA3);
                }else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonA4.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteA4 = buttonA4.getText().toString();
                    izabranaSedista.add(sedisteA4);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteA4 = buttonA4.getText().toString();
                    izabranaSedista.remove(sedisteA4);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonA5.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteA5 = buttonA5.getText().toString();
                    izabranaSedista.add(sedisteA5);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteA5 = buttonA5.getText().toString();
                    izabranaSedista.remove(sedisteA5);
                }
                else {
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonB1.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteB1 = buttonB1.getText().toString();
                    izabranaSedista.add(sedisteB1);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteB1 = buttonB1.getText().toString();
                    izabranaSedista.remove(sedisteB1);
                }
                else {
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonB2.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteB2 = buttonB2.getText().toString();
                    izabranaSedista.add(sedisteB2);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteB2 = buttonB2.getText().toString();
                    izabranaSedista.remove(sedisteB2);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonB3.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteB3 = buttonB3.getText().toString();
                    izabranaSedista.add(sedisteB3);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteB3 = buttonB3.getText().toString();
                    izabranaSedista.remove(sedisteB3);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonB4.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteB4 = buttonB4.getText().toString();
                    izabranaSedista.add(sedisteB4);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteB4 = buttonB4.getText().toString();
                    izabranaSedista.remove(sedisteB4);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonB5.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteB5 = buttonB5.getText().toString();
                    izabranaSedista.add(sedisteB5);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteB5 = buttonB5.getText().toString();
                    izabranaSedista.remove(sedisteB5);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonC1.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteC1 = buttonC1.getText().toString();
                    izabranaSedista.add(sedisteC1);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteC1 = buttonC1.getText().toString();
                    izabranaSedista.remove(sedisteC1);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonC2.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteC2 = buttonC2.getText().toString();
                    izabranaSedista.add(sedisteC2);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteC2 = buttonC2.getText().toString();
                    izabranaSedista.remove(sedisteC2);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonC3.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteC3 = buttonC3.getText().toString();
                    izabranaSedista.add(sedisteC3);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteC3 = buttonC3.getText().toString();
                    izabranaSedista.remove(sedisteC3);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonC4.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteC4 = buttonC4.getText().toString();
                    izabranaSedista.add(sedisteC4);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteC4 = buttonC4.getText().toString();
                    izabranaSedista.remove(sedisteC4);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonC5.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteC5 = buttonC5.getText().toString();
                    izabranaSedista.add(sedisteC5);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteC5 = buttonC5.getText().toString();
                    izabranaSedista.remove(sedisteC5);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonD1.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteD1 = buttonD1.getText().toString();
                    izabranaSedista.add(sedisteD1);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteD1 = buttonD1.getText().toString();
                    izabranaSedista.remove(sedisteD1);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonD2.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteD2 = buttonD2.getText().toString();
                    izabranaSedista.add(sedisteD2);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteD2 = buttonD2.getText().toString();
                    izabranaSedista.remove(sedisteD2);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonD3.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteD3 = buttonD3.getText().toString();
                    izabranaSedista.add(sedisteD3);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteD3 = buttonD3.getText().toString();
                    izabranaSedista.remove(sedisteD3);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonD4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonD4.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteD4 = buttonD4.getText().toString();
                    izabranaSedista.add(sedisteD4);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteD4 = buttonD4.getText().toString();
                    izabranaSedista.remove(sedisteD4);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonD5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonD5.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteD5 = buttonD5.getText().toString();
                    izabranaSedista.add(sedisteD5);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteD5 = buttonD5.getText().toString();
                    izabranaSedista.remove(sedisteD5);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonE1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonE1.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteE1 = buttonE1.getText().toString();
                    izabranaSedista.add(sedisteE1);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteE1 = buttonE1.getText().toString();
                    izabranaSedista.remove(sedisteE1);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonE2.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteE2 = buttonE2.getText().toString();
                    izabranaSedista.add(sedisteE2);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteE2 = buttonE2.getText().toString();
                    izabranaSedista.remove(sedisteE2);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonE3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonE3.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteE3 = buttonE3.getText().toString();
                    izabranaSedista.add(sedisteE3);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteE3 = buttonE3.getText().toString();
                    izabranaSedista.remove(sedisteE3);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonE4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonE4.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteE4 = buttonE4.getText().toString();
                    izabranaSedista.add(sedisteE4);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteE4 = buttonE4.getText().toString();
                    izabranaSedista.remove(sedisteE4);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonE5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonE5.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteE5 = buttonE5.getText().toString();
                    izabranaSedista.add(sedisteE5);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteE5 = buttonE5.getText().toString();
                    izabranaSedista.remove(sedisteE5);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonF1.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteF1 = buttonF1.getText().toString();
                    izabranaSedista.add(sedisteF1);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteF1 = buttonF1.getText().toString();
                    izabranaSedista.remove(sedisteF1);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonF2.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteF2 = buttonF2.getText().toString();
                    izabranaSedista.add(sedisteF2);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteF2 = buttonF2.getText().toString();
                    izabranaSedista.remove(sedisteF2);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonF3.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteF3 = buttonF3.getText().toString();
                    izabranaSedista.add(sedisteF3);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteF3 = buttonF3.getText().toString();
                    izabranaSedista.remove(sedisteF3);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonF4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonF4.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteF4 = buttonF4.getText().toString();
                    izabranaSedista.add(sedisteF4);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteF4 = buttonF4.getText().toString();
                    izabranaSedista.remove(sedisteF4);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonF5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorDrawable buttonColor = (ColorDrawable) buttonF5.getBackground();
                int color = buttonColor.getColor();
                if(color == Color.parseColor("#AAAAAA")){
                    v.setBackgroundColor(Color.parseColor("#FFEB3B"));
                    String sedisteF5 = buttonF5.getText().toString();
                    izabranaSedista.add(sedisteF5);
                }
                else if(color == Color.parseColor("#FFEB3B")){
                    v.setBackgroundColor(Color.parseColor("#AAAAAA"));
                    String sedisteF5 = buttonF5.getText().toString();
                    izabranaSedista.remove(sedisteF5);
                }
                else{
                    Toast.makeText(getActivity(), "Izabrano sedište je već rezervisano", Toast.LENGTH_LONG).show();
                }
            }
        });

        final Dialog myDialog = new Dialog(getContext());
        myDialog.setContentView(R.layout.dialog_rezervacija);

        buttonRez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editorPref4.putString("izabranaSedista", izabranaSedista.toString());
                editorPref4.commit();
                pref3=getContext().getSharedPreferences("item_projekcija", Context.MODE_PRIVATE);
                TextView dialogFilm = myDialog.findViewById(R.id.textViewDialogInputFilm);
                TextView dialogProjekcija = myDialog.findViewById(R.id.textViewDialogInputProjekcija);
                TextView dialogSala = myDialog.findViewById(R.id.textViewDialogInputSala);
                TextView dialogDatum = myDialog.findViewById(R.id.textViewDialogInputDatum);
                TextView dialogSedista = myDialog.findViewById(R.id.textViewDialogSedistaInput);
                TextView dialogCena = myDialog.findViewById(R.id.textViewDialogInputCena);
                Button dialogPotvdi = myDialog.findViewById(R.id.buttonPotrvdi);
                Button dialogOdustani = myDialog.findViewById(R.id.buttonOdustani);

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("id", pref3.getInt("idProjekcija", 0));
                    sendPostRequest2(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialogFilm.setText(pref2.getString("film", null));
                dialogProjekcija.setText(pref3.getString("vreme", null));
                dialogSala.setText(pref3.getString("sala", null));
                dialogDatum.setText(pref3.getString("datum", null));
                dialogSedista.setText(pref4.getString("izabranaSedista", null));
                dialogCena.setText(Float.toString(pref3.getFloat("cena", 0)*izabranaSedista.size()));

                dialogOdustani.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });
                dialogPotvdi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pref = getContext().getSharedPreferences("user_details", Context.MODE_PRIVATE);
                        JSONObject jsonObject1 = new JSONObject();
                        try {
                            jsonObject1.put("brojKarata", izabranaSedista.size());
                            jsonObject1.put("idProjekcija", pref3.getInt("idProjekcija", 0));
                            jsonObject1.put("idKorisnik", pref.getInt("id", 0));
                            sendPostRequest3(jsonObject1);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        myDialog.dismiss();
                    }
                });
                myDialog.show();
                //Toast.makeText(getActivity(), "Izabrana sedista "+pref4.getString("izabranaSedista", null)+", izabrana projekcija "+pref3.getInt("idProjekcija", 0), Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }

    public void sendPostRequest(final JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/projekcija/getAllProjekcijeForFilm";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    List<ItemProjekcija> data = new ArrayList<ItemProjekcija>();
                    for(int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String vreme = jsonObject1.getString("vreme");
                        String sala = jsonObject1.getString("sala");
                        int id = jsonObject1.getInt("id");
                        ItemProjekcija itemProjekcija = new ItemProjekcija(vreme, sala, id);
                        data.add(itemProjekcija);
                    }
                    ProjekcijaAdapter projekcijaAdapter = new ProjekcijaAdapter(data, getContext());
                    recyclerView.setAdapter(projekcijaAdapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
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

    public void sendPostRequest2(JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/projekcija/getProjekcijaById";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                SharedPreferences.Editor editorPref3 = pref3.edit();
                try {
                    String vreme = response.getString("vreme");
                    String sala = response.getString("sala");
                    double cena = response.getDouble("cena");
                    String datum = response.getString("datum");
                    editorPref3.putString("vreme", vreme);
                    editorPref3.putString("sala", sala);
                    editorPref3.putFloat("cena", (float) cena);
                    editorPref3.putString("datum", datum);
                    editorPref3.commit();
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

    public void sendPostRequest3(JSONObject jsonObject){
        String url = "http://10.0.2.2:8080/Bioskop/rezervacija/saveRezervacija";
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
        Toast.makeText(getActivity(), "Uspešno ste kreirali rezervaciju", Toast.LENGTH_LONG).show();
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
