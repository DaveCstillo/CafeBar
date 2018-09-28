package app.davecstillo.com.cafebar;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.Menu;
import android.view.MenuItem;

import app.davecstillo.com.cafebar.ServerConnection.httpHandler;

public class menuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        httpHandler.instance = new httpHandler("https://cafebarsite.000webhostapp.com/");
        Images.init();
        changeFragment(new bar_map());
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        return true;
    }
}
