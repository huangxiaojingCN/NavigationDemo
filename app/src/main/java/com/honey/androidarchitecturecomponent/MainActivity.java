package com.honey.androidarchitecturecomponent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        setUpBottomNav(navController);
        setUpSideNav(navController);
    }

    private void setUpBottomNav(NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    private void setUpSideNav(NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean navigated = NavigationUI.onNavDestinationSelected(item, navController);
        return navigated || super.onOptionsItemSelected(item);
    }
}
