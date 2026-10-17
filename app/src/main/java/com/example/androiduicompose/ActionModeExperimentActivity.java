package com.example.androiduicompose;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.view.ActionMode;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionModeExperimentActivity extends BaseActivity {

    private final List<String> items = new ArrayList<>(
            Arrays.asList("One", "Two", "Three", "Four", "Five")
    );
    private ActionModeItemAdapter adapter;
    private ActionMode actionMode;
    private MaterialToolbar toolbar;
    private int selectedPosition = ListView.INVALID_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewWithInsets(R.layout.activity_action_mode_list);
        toolbar = setupToolbar(R.id.toolbar);

        ListView listView = findViewById(R.id.action_mode_list);
        adapter = new ActionModeItemAdapter(this, items);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            startContextualActionMode(position);
            return true;
        });
    }

    private void startContextualActionMode(int position) {
        selectedPosition = position;
        adapter.setSelectedPosition(position);
        toolbar.setVisibility(View.GONE);
        if (actionMode != null) {
            actionMode.invalidate();
            return;
        }

        actionMode = startSupportActionMode(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.action_mode_menu, menu);
                mode.setTitle(R.string.action_mode_selected);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                mode.setTitle(R.string.action_mode_selected);
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete
                        && selectedPosition != ListView.INVALID_POSITION
                        && selectedPosition < items.size()) {
                    String removed = items.remove(selectedPosition);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(
                            ActionModeExperimentActivity.this,
                            "已删除：" + removed,
                            Toast.LENGTH_SHORT
                    ).show();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                actionMode = null;
                selectedPosition = ListView.INVALID_POSITION;
                adapter.setSelectedPosition(ListView.INVALID_POSITION);
                toolbar.setVisibility(View.VISIBLE);
            }
        });
    }

    private static final class ActionModeItemAdapter extends BaseAdapter {

        private final Context context;
        private final List<String> items;
        private int selectedPosition = ListView.INVALID_POSITION;

        ActionModeItemAdapter(Context context, List<String> items) {
            this.context = context;
            this.items = items;
        }

        void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public String getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RowViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context)
                        .inflate(R.layout.item_action_mode, parent, false);
                holder = new RowViewHolder();
                holder.root = convertView;
                holder.name = convertView.findViewById(R.id.action_item_name);
                holder.hint = convertView.findViewById(R.id.action_item_hint);
                convertView.setTag(holder);
            } else {
                holder = (RowViewHolder) convertView.getTag();
            }

            holder.name.setText(items.get(position));
            boolean isSelected = position == selectedPosition;
            holder.root.setBackgroundColor(context.getColor(
                    isSelected ? R.color.action_mode_blue : R.color.card_background
            ));
            int foregroundColor = context.getColor(
                    isSelected ? R.color.white : R.color.text_primary
            );
            int hintColor = context.getColor(
                    isSelected ? R.color.white : R.color.text_secondary
            );
            holder.name.setTextColor(foregroundColor);
            holder.hint.setTextColor(hintColor);
            return convertView;
        }
    }

    private static final class RowViewHolder {
        View root;
        TextView name;
        TextView hint;
    }
}
