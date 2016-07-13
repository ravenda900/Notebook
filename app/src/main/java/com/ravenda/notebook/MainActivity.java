package com.ravenda.notebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public static String PACKAGE_NAME;
    public static String NOTE_ID_EXTRA;
    public static String NOTE_TITLE_EXTRA;
    public static String NOTE_MESSAGE_EXTRA;
    public static String NOTE_CATEGORY_EXTRA;
    public static String NOTE_FRAGMENT_TO_LOAD_EXTRA;

    public enum FragmentToLaunch { VIEW, EDIT, CREATE }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        NOTE_ID_EXTRA = PACKAGE_NAME + ".Identifier";
        NOTE_TITLE_EXTRA = PACKAGE_NAME + ".Title";
        NOTE_MESSAGE_EXTRA = PACKAGE_NAME + ".Message";
        NOTE_CATEGORY_EXTRA = PACKAGE_NAME + ".Category";
        NOTE_FRAGMENT_TO_LOAD_EXTRA = PACKAGE_NAME + ".Fragment_To_Load";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent;

        switch (id) {
            case R.id.action_settings:
                intent = new Intent(this, AppPreferences.class);

                startActivity(intent);
                return true;
            case R.id.action_add_note:
                intent = new Intent(this, NoteDetailActivity.class);

                intent.putExtra(MainActivity.NOTE_FRAGMENT_TO_LOAD_EXTRA, FragmentToLaunch.CREATE);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean("background_color", false);

        if (isBackgroundDark) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainActivityLayout);

            mainLayout.setBackgroundColor(Color.parseColor("#3C3F41"));
        }

        String notebookTitle = sharedPreferences.getString("title", "Notebook");
        setTitle(notebookTitle);
    }
}
