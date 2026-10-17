package com.example.androiduicompose;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithInsets(R.layout.activity_main);

        findViewById(R.id.card_list_view).setOnClickListener(v ->
                openExperiment(ListViewExperimentActivity.class));
        findViewById(R.id.card_custom_dialog).setOnClickListener(v ->
                openExperiment(CustomDialogExperimentActivity.class));
        findViewById(R.id.card_xml_menu).setOnClickListener(v ->
                openExperiment(XmlMenuExperimentActivity.class));
        findViewById(R.id.card_action_mode).setOnClickListener(v ->
                openExperiment(ActionModeExperimentActivity.class));
        findViewById(R.id.card_recycler).setOnClickListener(v ->
                openExperiment(RecyclerExperimentActivity.class));
    }

    private void openExperiment(Class<?> activityClass) {
        startActivity(new Intent(this, activityClass));
    }
}
