package com.example.app.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.app.R;
import com.google.android.material.navigation.NavigationView;

public class Drawerlayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
DrawerLayout mDrawerlayout ;
NavigationView navigationView;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hoks
        setContentView(R.layout.activity_drawerlayout);
mDrawerlayout = findViewById(R.id.drawer_layot);
navigationView = findViewById(R.id.nav_view);
toolbar = findViewById(R.id.toolbar);
/*----------------------------Toolbat------------------------------*/
      setSupportActionBar(toolbar);
      /*---------------------Nav-------------*/
        navigationView.bringToFront();



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , mDrawerlayout,toolbar,R.string.nav_open,R.string.nav_close);
        mDrawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (mDrawerlayout.isDrawerOpen(GravityCompat.START)){
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.nav_home:
                Intent intent = new Intent();
            case R.id.nav_logout:
                break;
        }
       return true;
    }
}
