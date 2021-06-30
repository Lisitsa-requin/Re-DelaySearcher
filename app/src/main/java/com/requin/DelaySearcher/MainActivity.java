package com.requin.DelaySearcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements RecyclerFragment.FragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        RecyclerFragment recyclerFragment = new RecyclerFragment();
        if(manager.findFragmentById(R.id.container) == null){
            transaction.replace(R.id.container, recyclerFragment).commit();
        }

    }

    @Override
    public void onRecycleEvent() {
        Toast.makeText(this, "Tap!", Toast.LENGTH_SHORT).show();
    }
}