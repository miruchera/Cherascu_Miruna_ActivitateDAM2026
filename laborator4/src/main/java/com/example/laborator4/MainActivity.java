package com.example.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;
    private Button btnDeschide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvRezultat = findViewById(R.id.tvRezultat);
        btnDeschide = findViewById(R.id.btnDeschideAdaugare);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // Am schimbat clasa, numele variabilei și cheia
                        Medicament medicamentPrimit = result.getData().getParcelableExtra("medicament_key");

                        if (medicamentPrimit != null) {
                            tvRezultat.setText(medicamentPrimit.toString());

                            Toast.makeText(this, "Obiect preluat cu succes!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        btnDeschide.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdaugareActivitate.class);
            launcher.launch(intent);
        });
    }
}