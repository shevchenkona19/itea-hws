package itea.contactstest2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = this;

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        getSupportActionBar().setTitle("Welcome!");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etUsername.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {
                    Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                    intent.putExtra("USERNAME", etUsername.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Must to fill in the forms", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
