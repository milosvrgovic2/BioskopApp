package com.example.bioskop;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import static android.content.Context.MODE_PRIVATE;

public class FragmentHome extends Fragment {

    Button buttonRepertoar, buttonTopFilmovi, buttonTrailer, buttonLokacija;
    CallBackFragment callBackFragment;
    SharedPreferences pref;
    TextView textView;
    ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        setHasOptionsMenu(true);
        pref = getContext().getSharedPreferences("user_details", MODE_PRIVATE);
        textView = view.findViewById(R.id.textView);
        textView.setText(pref.getString("ime", null)+", dobrodošli u CINEMAGIC bioskop.");
        viewPager = view.findViewById(R.id.viewPager);
        ImageAdapter imageAdapter = new ImageAdapter(getContext());
        viewPager.setAdapter(imageAdapter);

        buttonRepertoar = view.findViewById(R.id.buttonRepertoar);
        buttonTopFilmovi = view.findViewById(R.id.buttonTopFilmovi);
        buttonTrailer = view.findViewById(R.id.buttonTrejleri);
        buttonLokacija = view.findViewById(R.id.buttonLokacija);

        buttonRepertoar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBackFragment!=null){
                    callBackFragment.changeFragment();
                }
            }
        });

        buttonTopFilmovi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentTopFilmovi());
                fragmentTransaction.commit();
            }
        });

        buttonTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentTrailer());
                fragmentTransaction.commit();
            }
        });

        buttonLokacija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.fragmentContainer, new FragmentMapView());
                fragmentTransaction.commit();
            }
        });

        return view;
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
