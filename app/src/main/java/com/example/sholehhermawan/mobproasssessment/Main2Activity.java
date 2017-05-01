package com.example.sholehhermawan.mobproasssessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabsActivity()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_daftar) {
            Intent intent = new Intent(Main2Activity.this, CatalogActivity.class);
            Main2Activity.this.startActivity(intent);
            Main2Activity.this.finish();
        } else if (id == R.id.nav_lihat) {
            Intent intent = new Intent(Main2Activity.this, DokterActivity.class);
            Main2Activity.this.startActivity(intent);
            Main2Activity.this.finish();
        } else if (id == R.id.nav_lihat_notifikasi) {
            Intent intent = new Intent(Main2Activity.this, NotifikasiActivity.class);
            Main2Activity.this.startActivity(intent);
            Main2Activity.this.finish();
        } else if (id == R.id.nav_tentang_app) {
            Intent intent = new Intent(Main2Activity.this, AplikasiActivity.class);
            Main2Activity.this.startActivity(intent);
            Main2Activity.this.finish();
        } else if (id == R.id.nav_keluar) {
            Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
            Main2Activity.this.startActivity(intent);
            Main2Activity.this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
