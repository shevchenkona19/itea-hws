package itea.logincall;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by shevc on 04.12.2016.
 */

public class CallActivity extends AppCompatActivity {

    private TextView tvName;
    private EditText etPhone;
    private TextInputLayout tilPhone;
    private Button btnCall;

    private static final int MIN_PHONE_LENGTH = 10;
    private static final int MAX_PHONE_LENGTH = 13;
    private static final String EMPTY_STRING = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        tvName = (TextView) findViewById(R.id.tvName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        tilPhone = (TextInputLayout) findViewById(R.id.tilPhone);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setVisibility(View.GONE);

        Intent intent = new Intent(getIntent());
        Bundle b = intent.getExtras();
        tvName.setText("Hello, " + b.getString("USERNAME"));

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callerint = new Intent();
                callerint.setAction(Intent.ACTION_CALL);
                callerint.setData(Uri.parse("tel:" + etPhone.getText().toString()));
                startActivity(callerint);
            }
        });

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tilPhone.setError(EMPTY_STRING);
                try {
                    switch (charSequence.charAt(0)) {
                        case '+':
                            if (charSequence.length() < MAX_PHONE_LENGTH) {
                                tilPhone.setError("Less than 12 numbers");
                                btnCall.setVisibility(View.GONE);
                                return;
                            }
                            char[] number = charSequence.subSequence(1, charSequence.length()).toString().toCharArray();
                            for (char c : number) {
                                if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                                        (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                                    tilPhone.setError("Phone number can contain only numbers!");
                                    btnCall.setVisibility(View.GONE);
                                    return;
                                }
                            }
                            if (charSequence.length() > MAX_PHONE_LENGTH) {
                                tilPhone.setError("More than 12 numbers");
                                btnCall.setVisibility(View.GONE);
                                return;
                            }
                            break;


                        case '0':
                            if (charSequence.length() < MIN_PHONE_LENGTH) {
                                tilPhone.setError("Less than 10 numbers");
                                btnCall.setVisibility(View.GONE);
                                break;
                            } else if (charSequence.length() > MIN_PHONE_LENGTH) {
                                tilPhone.setError("More than 10 numbers");
                                btnCall.setVisibility(View.GONE);
                                return;
                            } else {
                                char[] number1 = charSequence.subSequence(0, charSequence.length()).toString().toCharArray();
                                for (char c : number1) {
                                    if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                                            (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                                        tilPhone.setError("Phone number can contain only numbers!");
                                        btnCall.setVisibility(View.GONE);
                                        return;
                                    }
                                }
                            }
                            break;
                        default:
                            tilPhone.setError("Enter a valid number");
                            btnCall.setVisibility(View.GONE);
                            return;
                    }
                    btnCall.setVisibility(View.VISIBLE);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }



}
