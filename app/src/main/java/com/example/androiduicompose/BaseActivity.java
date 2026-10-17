package com.example.androiduicompose;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.appbar.MaterialToolbar;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
    }

    protected void setContentViewWithInsets(@LayoutRes int layoutRes) {
        setContentView(layoutRes);
        View root = findViewById(R.id.root);
        if (root == null) {
            return;
        }

        WindowInsetsControllerCompat windowInsetsController =
                WindowCompat.getInsetsController(getWindow(), root);
        windowInsetsController.setAppearanceLightStatusBars(true);
        windowInsetsController.setAppearanceLightNavigationBars(true);

        int startLeft = root.getPaddingLeft();
        int startTop = root.getPaddingTop();
        int startRight = root.getPaddingRight();
        int startBottom = root.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(root, (view, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(
                    startLeft + bars.left,
                    startTop + bars.top,
                    startRight + bars.right,
                    startBottom + bars.bottom
            );
            return windowInsets;
        });
        ViewCompat.requestApplyInsets(root);
    }

    protected MaterialToolbar setupToolbar(@IdRes int toolbarId) {
        MaterialToolbar toolbar = findViewById(toolbarId);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
        return toolbar;
    }
}
