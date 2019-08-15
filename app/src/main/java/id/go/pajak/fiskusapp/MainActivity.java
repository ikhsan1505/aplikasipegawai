package id.go.pajak.fiskusapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import id.go.pajak.fiskusapp.fragments.GalleryFragment;
import id.go.pajak.fiskusapp.fragments.HelpFragment;
import id.go.pajak.fiskusapp.fragments.HomeFragment;
import id.go.pajak.fiskusapp.fragments.ShareFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle drawerToggle;
    TextView tvUnitkerja;
    TextView tvNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);

        String cekDbPresent=dbHelper.cekDbPresent();

        //Log.e("onCreate: ",cekDbPresent );
        if(cekDbPresent =="Tidak"){
           Intent gotoPreconfig = new Intent(MainActivity.this,PreconfigActivity.class);
            startActivity(gotoPreconfig);
        }

        tvNama = findViewById(R.id.tvNama);
        tvUnitkerja = findViewById(R.id.tvUnitkerja);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout.addDrawerListener(drawerToggle);

        if(setupNavDrawer(navigationView)){
            setupNavDrawer(navigationView);
        }else {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }


    }

    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(
                this,
                mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private boolean setupNavDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new HomeFragment();
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_gallery:
                        fragment = new GalleryFragment();
                        break;
                    case R.id.nav_share:
                        fragment = new ShareFragment();
                        break;
                    case R.id.nav_help:
                        fragment = new HelpFragment();
                        break;
                    default:
                        fragment = new HomeFragment();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                mDrawerLayout.closeDrawers();

                return true;
            }
        });
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}
