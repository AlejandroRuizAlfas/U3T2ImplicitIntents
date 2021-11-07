package dam.androidalejandror.u3t2implicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MoreIntents extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail, etSubject, etBody, etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_intents);

        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        }

        setUI();
    }

    private void setUI() {
        Button btIntent1, btIntent2, btIntent3;

        etEmail = findViewById(R.id.etEmail);
        etEmail.setHint("E-mail of the reciever...");
        etSubject = findViewById(R.id.etSubject);
        etSubject.setHint("Subject or title of the email...");
        etBody = findViewById(R.id.etBody);
        etBody.setHint("Body or text...");

        etPhone = findViewById(R.id.etPhone);
        etPhone.setHint("Phone Number...");

        btIntent1 = findViewById(R.id.btIntent1);
        btIntent2 = findViewById(R.id.btIntent2);
        btIntent3 = findViewById(R.id.btIntent3);

        btIntent1.setOnClickListener(this);
        btIntent2.setOnClickListener(this);
        btIntent3.setOnClickListener(this);
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String address, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openSettings() {
        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btIntent1:
                composeEmail(etEmail.getText().toString(),etSubject.getText().toString(),etBody.getText().toString());
                break;
            case R.id.btIntent2:
                dialPhoneNumber(etPhone.getText().toString());
                break;
            case R.id.btIntent3:
               openSettings();
                break;
        }
    }
}