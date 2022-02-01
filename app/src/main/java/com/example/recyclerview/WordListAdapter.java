package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LinkedList<String> wordList;
    private LayoutInflater layoutInflater;

    // al constructor hace falta context
    public WordListAdapter(LinkedList<String> wordList, Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_row,parent,false);
        return new WordViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {

        String current = wordList.get(position);
        holder.textViewItem.setText(current);
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }


    /**
     * Clase interna de WordViewAdapter
     */
    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // atributos que añadimo
        public final TextView textViewItem;
        final WordListAdapter adapter;

        // añadimos atributos al constructor
        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            textViewItem = (TextView) itemView.findViewById(R.id.textview_item);
            this.adapter = adapter;
            // añadimos onclic listener
            textViewItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // identificamos la posicion del layout
            int posicion = getLayoutPosition();

            // cojemos texto en la posicione indicada
            String element = wordList.get(posicion);

            // cambiamos texto
            wordList.set(posicion,"BOOM! "+element);

            // notificamos adaptador de lo scambios
            // actualizamos para mostrar nuevos datos
            adapter.notifyDataSetChanged();

            // ahora hay que añadit setlistener en el constructor
        }
    }
}
