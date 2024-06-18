package com.example.menu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TareAdapter.OnItemClickListener {

    private List<String> tasks = new ArrayList<>();
    private RecyclerView recyclerViewTasks;
    private TareAdapter taskAdapter;
    private EditText editTextTask;
    private Button buttonAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);

        taskAdapter = new TareAdapter(tasks, this);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        buttonAddTask.setOnClickListener(v -> {
            String task = editTextTask.getText().toString();
            if (!task.isEmpty()) {
                tasks.add(task);
                taskAdapter.notifyItemInserted(tasks.size() - 1);
                editTextTask.setText("");
            }
        });
    }

    @Override
    public void onEditClick(int position) {
        editTask(position);
    }

    @Override
    public void onDeleteClick(int position) {
        deleteTask(position);
    }

    @Override
    public void onMarkClick(int position) {
        
        taskAdapter.notifyItemChanged(position);
    }

    private void editTask(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Editar Tarea");

        final EditText editText = new EditText(this);
        editText.setText(tasks.get(position));
        builder.setView(editText);

        builder.setPositiveButton("Guardar", (dialog, which) -> {
            String editedTask = editText.getText().toString().trim();
            if (!editedTask.isEmpty()) {
                tasks.set(position, editedTask);
                taskAdapter.notifyItemChanged(position);
                Toast.makeText(MainActivity.this, "Tarea editada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Por favor, introduce un texto válido", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void deleteTask(int position) {
        tasks.remove(position);
        taskAdapter.notifyItemRemoved(position);
    }
}
