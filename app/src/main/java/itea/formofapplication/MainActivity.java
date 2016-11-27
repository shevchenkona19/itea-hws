package itea.formofapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private Button btnSend;
    private RadioGroup rgWorkSchedule;
    private EditText etName, etLastName, etEmail;
    private MyTextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button) findViewById(R.id.btnSend);
        rgWorkSchedule = (RadioGroup) findViewById(R.id.rgWorkSchedule);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etName = (EditText) findViewById(R.id.etName);

        textWatcher = new MyTextWatcher(new EditText[] {etName, etLastName, etEmail});

        etName.addTextChangedListener(textWatcher);
        etEmail.addTextChangedListener(textWatcher);
        etLastName.addTextChangedListener(textWatcher);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rgWorkSchedule.getCheckedRadioButtonId() == -1 ){
                    Toast.makeText(MainActivity.this, "You haven't choose a work schedule", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, textWatcher.getName() + " " + textWatcher.getLastName() + " " + textWatcher.getEmail(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
