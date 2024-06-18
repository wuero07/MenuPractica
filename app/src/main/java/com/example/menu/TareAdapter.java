package com.example.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TareAdapter extends RecyclerView.Adapter<TareAdapter.ViewHolder> {

    private List<String> tasks;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
        void onMarkClick(int position);
    }

    public TareAdapter(List<String> tasks, OnItemClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarea_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String task = tasks.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTask;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTask = itemView.findViewById(R.id.text_nombre_tarea);
        }

        public void bind(String task, OnItemClickListener listener) {
            textViewTask.setText(task);


            itemView.setOnClickListener(v -> listener.onEditClick(getAdapterPosition()));


            itemView.findViewById(R.id.button_delete).setOnClickListener(v -> listener.onDeleteClick(getAdapterPosition()));


            itemView.findViewById(R.id.button_mark).setOnClickListener(v -> listener.onMarkClick(getAdapterPosition()));
        }
    }
}


