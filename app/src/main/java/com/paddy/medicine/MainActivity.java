package com.paddy.medicine;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int NAV_ITEMS_MAIN = R.id.group_main;
    private DrawerLayout mDrawerLayout;
    Button order_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initializeNavigationDrawer();
        order_now = (Button) findViewById(R.id.order_now);
        order_now.setOnClickListener(this);


    }
    /**
     * Initialize navigation drawer
     */
    protected void initializeNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        // set onClicklistener on user profile layout
        findViewById(R.id.ll_user_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.closeDrawers();
//                createView(new UserProfileActivity(), R.string.profile);
//                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
//                startActivity(intent);
            }
        });
    }
    /**
     * Setup menus action
     *
     * @param navigationView
     */
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //if an item from extras group is clicked,refresh NAV_ITEMS_MAIN to remove previously checked item
                navigationView.getMenu().setGroupCheckable(NAV_ITEMS_MAIN, (menuItem.getGroupId() == NAV_ITEMS_MAIN), true);

//                //Update highlighted item in the navigation menu
//                if (menuItem.getGroupId() != NAV_ITEMS_EXTRA) {
//                    menuItem.setChecked(true);
//                }

                switch (menuItem.getItemId()) {
                    case R.id.item_place_order:
                        //createView(new DealFragment(), R.string.title_deal);
                        break;
                    case R.id.item_medical_history:
                        // createView(new CouponFragment(), R.string.title_coupon);
                        break;
                    case R.id.item_share:
                        // createView(new TopProductsFragment(), R.string.title_price_comparison);
                        break;
                    case R.id.item_about_us:
                        // createView(new DealFragment(), R.string.title_stores);
                        break;

                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * Create fragment view : returns on click menu item
     *
     * @param fragment
     * @param title
     */
    private void createView(Fragment fragment, int title) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_layout, fragment, getApplicationContext().getText(title).toString())
                .addToBackStack(null)
                .commit();
    }

    public void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Place Order");
        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
//        toolBarHeight = toolbar.getHeight()
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == order_now){
            Intent intent = new Intent(MainActivity.this,OrderNowActivity.class);
            startActivity(intent);
        }
    }
}
