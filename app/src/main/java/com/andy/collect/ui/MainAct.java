package com.andy.collect.ui;

import android.content.Intent;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andy.collect.R;

public class MainAct extends BaseAct implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private final long EXIT_TIME = 2000L;
    private long exitTime = 0;

    @Override
    protected int loadLayoutId() {
        return R.layout.act_main;
    }

    @Override
    protected void initViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

    }

    @Override
    protected void initData() {
        toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null)
            drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (navigationView != null) {
            disableNavigationViewScrollbars(navigationView);
            navigationView.setNavigationItemSelectedListener(this);
            Menu navigationMenu = navigationView.getMenu();
            initializeFlexDirectionSpinner(navigationMenu);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeFlexDirectionSpinner(Menu navigationMenu) {
        initializeSpinner(R.id.nav_camera, navigationMenu, R.array.array_sub_menu, new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectStr = parent.getItemAtPosition(position).toString();
                showWarnToast(selectStr);
                if (selectStr.equals("sub_menu_1")) {
                    startActivity(new Intent(MainAct.this, TRefreshAct_1.class));
                } else if (selectStr.equals("sub_menu_2")) {

                } else if (selectStr.equals("sub_menu_3")) {

                } else if (selectStr.equals("sub_menu_4")) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initializeSpinner(int menuItemId, Menu navigationMenu,
                                   int arrayResourceId, AdapterView.OnItemSelectedListener listener) {
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(navigationMenu.findItem(menuItemId));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayResourceId, R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);
    }

    @Override
    protected void bindListener() {
        super.bindListener();
        navigationView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //隐藏NavigationViewScrollbar的滚动条
    private void disableNavigationViewScrollbars(NavigationView navigationView) {
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        if (navigationMenuView != null) {
            navigationMenuView.setVerticalScrollBarEnabled(false);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > EXIT_TIME) {
                showFailToast("再按一次，将退出程序！");
                exitTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
