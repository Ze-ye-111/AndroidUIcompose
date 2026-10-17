package com.example.androiduicompose;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewExperimentActivity extends BaseActivity {

    private static final String CHANNEL_ID = "animal_selection";
    private static final int NOTIFICATION_PERMISSION_REQUEST = 3001;
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";

    private final String[] animalNames = {
            "Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"
    };
    private final int[] animalImages = {
            R.drawable.lion, R.drawable.tiger, R.drawable.monkey,
            R.drawable.dog, R.drawable.cat, R.drawable.elephant
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithInsets(R.layout.activity_animal_list);
        setupToolbar(R.id.toolbar);

        createNotificationChannel();
        requestNotificationPermission();

        List<Map<String, Object>> rows = new ArrayList<>();
        for (int i = 0; i < animalNames.length; i++) {
            Map<String, Object> row = new HashMap<>();
            row.put(KEY_NAME, animalNames[i]);
            row.put(KEY_IMAGE, animalImages[i]);
            rows.add(row);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                rows,
                R.layout.item_animal_simple,
                new String[]{KEY_NAME, KEY_IMAGE},
                new int[]{R.id.item_name, R.id.item_image}
        );
        adapter.setViewBinder((view, data, textRepresentation) -> {
            if (view.getId() == R.id.item_image && data instanceof Integer) {
                ((android.widget.ImageView) view).setImageResource((Integer) data);
                return true;
            }
            return false;
        });

        ListView listView = findViewById(R.id.animal_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedName = animalNames[position];
            Toast.makeText(this, "选中：" + selectedName, Toast.LENGTH_SHORT).show();
            sendSelectionNotification(selectedName, position);
        });
    }

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    NOTIFICATION_PERMISSION_REQUEST
            );
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "动物列表通知",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("实验三 ListView 选中项通知");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void sendSelectionNotification(String selectedName, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "请先允许通知权限", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, ListViewExperimentActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                position,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, CHANNEL_ID);
        } else {
            builder = new Notification.Builder(this);
        }

        Notification notification = builder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setContentTitle(selectedName)
                .setContentText("你选中了 " + selectedName + "，点击通知可返回动物列表。")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.notify(1000 + position, notification);
    }
}
