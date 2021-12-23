package com.example.appbase;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import android.widget.ImageView;

import com.example.appbase.Adapters.PageAdapter;
import com.example.appbase.Fragments.FragmenContacts;
import com.example.appbase.Fragments.FragmenPosts;
import com.example.appbase.Fragments.FragmentCont;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class Navegacion extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    public Navegacion() {
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_menu_navegacion);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
        setupTabLayout();


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


    private void setupTabLayout() {
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.design_default_color_on_primary)));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));

        tabLayout.setSelectedTabIndicatorHeight(2);
        tabLayout.setTabGravity(0);
    }


    private void setupViewPager() {
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        Fragment fragDos = new FragmenPosts();
        Fragment fragUno = new FragmenContacts();

        adapter.addFragment(fragUno, "Contacts");
        adapter.addFragment(fragDos, "Posts");

        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }



}















