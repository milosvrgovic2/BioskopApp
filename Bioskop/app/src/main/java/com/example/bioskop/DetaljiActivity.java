package com.example.bioskop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DetaljiActivity extends AppCompatActivity implements CallBackFragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);

        addFragment();
    }

    public void addFragment(){
        FragmentDetalji fragment = new FragmentDetalji();
        fragment.setCallBackFragment(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    public void replaceFragment(){
        FragmnetRezervacija fragmentRezervacija = new FragmnetRezervacija();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragmentContainer, fragmentRezervacija);
        fragmentTransaction.commit();
    }

    @Override
    public void changeFragment() {
        replaceFragment();
    }
}
