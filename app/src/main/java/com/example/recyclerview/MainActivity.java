package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    final String TAG = "recyclerView";
    private final LinkedList<String> wordList = new LinkedList<>();
    private int contador = 0;
    private RecyclerView recyclerView;
    private WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, " entra OnCreate");

        for (int i = 0 ; i < 130; i++) {
            wordList.addLast("word " + contador++);
            Log.d(TAG, wordList.getLast());
        }
        // identificamos fab y lo ponemos a la escucha
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordList.addLast("word "+wordList.size());
                // notificar adaptador sobre cambios y mostrar data
                recyclerView.getAdapter().notifyItemInserted(wordList.size());
                // mostrar ultima posicion
                recyclerView.smoothScrollToPosition(wordList.size());
            }
        });
        // identificamos recycleView en layout
        recyclerView = findViewById(R.id.recyclerView);

        // Creamos adaptador personalizado
        adapter = new WordListAdapter(wordList, this);

        // Conectamos adaptador
        recyclerView.setAdapter(adapter);

        // indicamos layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}