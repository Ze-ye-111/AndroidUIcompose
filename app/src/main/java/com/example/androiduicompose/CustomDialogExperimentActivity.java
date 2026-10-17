package com.example.androiduicompose;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class CustomDialogExperimentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithInsets(R.layout.activity_custom_dialog);
        setupToolbar(R.id.toolbar);
        findViewById(R.id.show_dialog_button).setOnClickListener(v -> showCustomDialog());
    }

    private void showCustomDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_sign_in, null, false);
        EditText usernameInput = dialogView.findViewById(R.id.input_username);
        EditText passwordInput = dialogView.findViewById(R.id.input_password);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Sign in", null)
                .create();

        dialog.setOnShowListener(ignored ->
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                    String username = usernameInput.getText().toString().trim();
                    String password = passwordInput.getText().toString();
                    if (TextUtils.isEmpty(username)) {
                        usernameInput.setError("请输入用户名");
                        usernameInput.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        passwordInput.setError("请输入密码");
                        passwordInput.requestFocus();
                        return;
                    }
                    Toast.makeText(this, "登录成功：" + username, Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }));
        dialog.show();
    }
}
