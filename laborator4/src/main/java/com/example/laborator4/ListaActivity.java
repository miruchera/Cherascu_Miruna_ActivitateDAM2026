package com.example.laborator4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {


    private ArrayList<Medicament> lista;
    private ArrayAdapter<Medicament> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
    //listview
        ListView lvMedicamente = findViewById(R.id.idLv);
        lista = getIntent().getParcelableArrayListExtra("lista_key");

        if(lista==null){
            lista = new ArrayList<>();
        }
    //adapter
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lista);
        lvMedicamente.setAdapter(adapter);

    //toast
        lvMedicamente.setOnItemClickListener((parent,view,position,id)->{
            Medicament medSelectat = lista.get(position);
            Toast.makeText(ListaActivity.this,medSelectat.toString(), Toast.LENGTH_LONG).show();
        });

    //long click stergere
        lvMedicamente.setOnItemLongClickListener((parent,view,position,id)->{
            lista.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ListaActivity.this, "Medicament sters", Toast.LENGTH_SHORT).show();
            return true;
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }
}