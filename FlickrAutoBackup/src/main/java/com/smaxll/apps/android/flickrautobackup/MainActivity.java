package com.smaxll.apps.android.flickrautobackup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.slf4j.LoggerFactory;

public class MainActivity extends Activity {

    private Menu menu;
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.menu = menu;

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        renderMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Render the options menu
     */
    private void renderMenu() {
        if (menu != null) {
            // set the trial info invisible if the customer is a paid customer
            menu.findItem(R.id.trial_info).setVisible(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.trial_info:
                LOG.debug("trail_info selected");

                this.startService(new Intent(this, UploadService.class));
                Toast.makeText(getApplicationContext(), "Starting service", Toast.LENGTH_SHORT);
                break;
            case R.id.preferences:
                LOG.debug("Preferences selected");
                startActivity(new Intent(this, Preferences.class));
                break;
            case R.id.faq:
                String url = "https://github.com/rafali/flickr-uploader/wiki/FAQ";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
