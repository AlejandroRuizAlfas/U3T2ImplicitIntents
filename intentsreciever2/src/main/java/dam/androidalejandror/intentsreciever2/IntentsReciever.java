package dam.androidalejandror.intentsreciever2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class IntentsReciever extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_reciever);

        setUI();
    }

    private void setUI() {
        Intent intent = getIntent();
        String textoCopiado = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView tvTextoCopiado = findViewById(R.id.tvCopiado);
        tvTextoCopiado.setText(textoCopiado);
    }
}