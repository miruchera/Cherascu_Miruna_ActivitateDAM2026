package com.example.laborator4;
//actualizare c sursa
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugareActivitate extends AppCompatActivity {

    private EditText et;
    private CheckBox cb;
    private RadioGroup rg;
    private Spinner sp;
    private RatingBar rb;
    private Switch sw;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adaugare_activitate);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Leg variabilele de interfața grafică (ID-urile din XML rămân la fel ca să nu se strice layout-ul)
        et = findViewById(R.id.etDenumire);
        cb = findViewById(R.id.cbSpini);
        rg = findViewById(R.id.rgMediu);
        sp = findViewById(R.id.spinnerCuloare);
        rb = findViewById(R.id.ratingBar);
        sw = findViewById(R.id.swPerena);
        btn = findViewById(R.id.btnSalvare);

        // Construiesc lista derulantă a spinner-ului (se potrivesc culorile și la medicamente)
        String[] culori = {"Roșu", "Alb", "Galben", "Roz", "Violet"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, culori);
        sp.setAdapter(adapter);

        btn.setOnClickListener(v -> {

            String denumire = et.getText().toString().trim();
            if (denumire.isEmpty()) {
                et.setError("Câmp obligatoriu!");
                return;
            }

            // Adaptăm preluarea datelor la atributele clasei Medicament
            boolean areEfecteSecundare = cb.isChecked();
            boolean necesitaReteta = sw.isChecked();
            float rating = rb.getRating();

            String culoare = sp.getSelectedItem().toString();

            int idSelectat = rg.getCheckedRadioButtonId();
            RadioButton rbSelectat = findViewById(idSelectat);
            String modAdministrare = rbSelectat.getText().toString();

            // Creăm obiectul Medicament în loc de Floare
            Medicament medicamentNou = new Medicament(denumire, areEfecteSecundare, modAdministrare, culoare, rating, necesitaReteta);

            Intent intent = new Intent();
            // Folosim aceeași cheie setată adineauri în MainActivity
            intent.putExtra("medicament_key", medicamentNou);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}