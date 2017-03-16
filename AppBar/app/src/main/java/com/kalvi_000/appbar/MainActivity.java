package com.kalvi_000.appbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings2:
                Toast.makeText(this, "action Settings 2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings3:
                Toast.makeText(this, "action Settings 3", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings4:
                Toast.makeText(this, "action Settings 4", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings5:
                Toast.makeText(this, "action Settings 5", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
