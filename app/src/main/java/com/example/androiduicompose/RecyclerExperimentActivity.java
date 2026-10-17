package com.example.androiduicompose;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerExperimentActivity extends BaseActivity {

    private final List<Animal> animals = new ArrayList<>();
    private RecyclerAnimalAdapter adapter;
    private RecyclerView recyclerView;
    private TextView countView;
    private long nextId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithInsets(R.layout.activity_recycler_animals);
        setupToolbar(R.id.toolbar);

        addAnimal("Lion", R.drawable.lion);
        addAnimal("Tiger", R.drawable.tiger);
        addAnimal("Monkey", R.drawable.monkey);
        addAnimal("Dog", R.drawable.dog);
        addAnimal("Cat", R.drawable.cat);
        addAnimal("Elephant", R.drawable.elephant);

        recyclerView = findViewById(R.id.animal_recycler);
        countView = findViewById(R.id.recycler_count);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAnimalAdapter(animals, this::addRecordAfterClick);
        recyclerView.setAdapter(adapter);
        updateCount();
    }

    private void addAnimal(String name, int imageResId) {
        animals.add(new Animal(nextId++, name, imageResId));
    }

    private void addRecordAfterClick(Animal clickedAnimal, int position) {
        Toast.makeText(
                this,
                "点击了第 " + (position + 1) + " 项：" + clickedAnimal.getName(),
                Toast.LENGTH_SHORT
        ).show();

        animals.add(new Animal(
                nextId++,
                clickedAnimal.getName(),
                clickedAnimal.getImageResId()
        ));
        int insertedPosition = animals.size() - 1;
        adapter.notifyItemInserted(insertedPosition);
        recyclerView.scrollToPosition(insertedPosition);
        updateCount();
    }

    private void updateCount() {
        countView.setText(getString(R.string.recycler_count, animals.size()));
    }
}
