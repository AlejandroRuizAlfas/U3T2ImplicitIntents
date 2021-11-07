package dam.androidalejandror.u3t2implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String IMPLICIT_INTENTS = "ImplicitIntents";
    private EditText etUri, etLocation, etText, etZoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        }

        setUI();
    }

    private void setUI() {
        Button btOpenUri, btOpenLocation, btShareText, btMoreIntents;

        etUri = findViewById(R.id.etUri);
        etLocation = findViewById(R.id.etLocation);
        etText = findViewById(R.id.etText);
        etZoom = findViewById(R.id.et_zoom);
        etZoom.setHint("Zoom...");

        btOpenUri = findViewById(R.id.btOpenUri);
        btOpenLocation = findViewById(R.id.btOpenLocation);
        btShareText = findViewById(R.id.btShareText);
        btMoreIntents = findViewById(R.id.btMoreIntents);

        btOpenUri.setOnClickListener(this);
        btOpenLocation.setOnClickListener(this);
        btShareText.setOnClickListener(this);
        btMoreIntents.setOnClickListener(this);
    }

    private void openWebsite(String urlText){
        Uri webpage = Uri.parse(urlText);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d(IMPLICIT_INTENTS, "openWebsite: Can't handle this intent!");
        }
    }

    private void openLocation(String location, int zoom) throws Exception {
        if (zoom < 0 || zoom > 23){
            throw new Exception("Valor incorrecto. Entre 1 y 23");
        }
        Uri addresUri = Uri.parse("geo:0,0?z=2"+zoom+"&q="+location);
        Intent intent = new Intent(Intent.ACTION_VIEW,addresUri);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d(IMPLICIT_INTENTS, "openWebsite: Can't handle this intent!");
        }
    }

    private void shareText(String text){
        new ShareCompat.IntentBuilder(this)
                .setType("text/plain")
                .setText(text)
                .startChooser();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btOpenUri:
                openWebsite(etUri.getText().toString());
                break;
            case R.id.btOpenLocation:
                try {
                    openLocation(etLocation.getText().toString(),Integer.parseInt(etZoom.getText().toString()));
                }catch (Exception e){
                    Log.d(IMPLICIT_INTENTS, "Exception in openWebsite: Valor incorrecto. Entre 1 y 23");
                    etZoom.setError("Valor incorrecto. Entre 1 y 23");
                }
                break;
            case R.id.btShareText:
                shareText(etText.getText().toString());
                break;
            case R.id.btMoreIntents:
                startActivity(new Intent(MainActivity.this,MoreIntents.class));
                break;
        }
    }

}