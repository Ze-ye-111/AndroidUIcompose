package com.example.androiduicompose;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class XmlMenuExperimentActivity extends BaseActivity {

    private TextView testText;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithInsets(R.layout.activity_xml_menu);
        toolbar = setupToolbar(R.id.toolbar);
        testText = findViewById(R.id.menu_test_text);
        findViewById(R.id.open_menu_button).setOnClickListener(v -> toolbar.showOverflowMenu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xml, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_font_small) {
            setTestTextSize(10);
            return true;
        }
        if (itemId == R.id.menu_font_medium) {
            setTestTextSize(16);
            return true;
        }
        if (itemId == R.id.menu_font_large) {
            setTestTextSize(20);
            return true;
        }
        if (itemId == R.id.menu_normal) {
            Toast.makeText(this, R.string.menu_normal_clicked, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (itemId == R.id.menu_color_red) {
            testText.setTextColor(Color.RED);
            return true;
        }
        if (itemId == R.id.menu_color_black) {
            testText.setTextColor(Color.BLACK);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setTestTextSize(int sizeSp) {
        testText.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeSp);
    }
}
