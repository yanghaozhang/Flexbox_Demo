package org.flexbox.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.flexbox.R;
import org.flexbox.ui.MainActivity;

import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainActivity activity;
    private List<String> list;

    public DragAdapter(MainActivity actiity, List<String> list) {
        this.activity = actiity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_drag, parent, false);
        return new DragViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((DragViewHolder) holder).tvName.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DragViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;

        public DragViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_Name);
        }
    }
}