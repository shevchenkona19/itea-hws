package itea.logincall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private LinearLayout mainLinearLayout;

    private Button btnGoogle, btnFacebook, btnLogin;
    private TextView tvForgot, tvTerms, tvSignup;
    private EditText etUsername, etPassword;
    private TextInputLayout tilUsername, tilPassword;

    private View.OnClickListener oclLogin;
    private View.OnClickListener oclOther;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);
        Bitmap myPic = BitmapFactory.decodeResource(getResources(),R.drawable.mainpic);
        mainLinearLayout.setBackground(new BitmapDrawable(getResources(), new BlurMaker().blur(this, myPic)));

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        btnGoogle = (Button) findViewById(R.id.btnGoogle);
        btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        tvForgot = (TextView) findViewById(R.id.tvForgot);
        tvSignup = (TextView) findViewById(R.id.tvSignup);
        tvTerms = (TextView) findViewById(R.id.tvTerms);

        etPassword = (EditText) findViewById(R.id.etPassword);
        etUsername = (EditText) findViewById(R.id.etUsername);

        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        tilUsername = (TextInputLayout) findViewById(R.id.tilUsername);

        oclLogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btnGoogle:
                        Toast.makeText(LoginActivity.this, "Google+", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btnFacebook:
                        Toast.makeText(LoginActivity.this, "Facebook", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.btnLogin:
                        if (username != "" && password != "") {
                            Toast.makeText(LoginActivity.this, "Username: " + username +
                                    "; Password: " + password, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this, CallActivity.class);
                            intent.putExtra("USERNAME", username);
                            startActivity(intent);
                            break;
                        } else {
                            Toast.makeText(LoginActivity.this, "You have to enter a password and a username",
                                    Toast.LENGTH_LONG).show();
                            break;
                        }
                }
            }
        };

        etUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                username = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                password = charSequence.toString();
                char[] number = charSequence.subSequence(1, charSequence.length()).toString().toCharArray();
                for (char c : number) {
                    if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                            (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                        tilPassword.setError("You can use only numbers");
                        return;
                    }
                }
                tilPassword.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnGoogle.setOnClickListener(oclLogin);
        btnFacebook.setOnClickListener(oclLogin);
        btnLogin.setOnClickListener(oclLogin);

        oclOther = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tvForgot:
                        Toast.makeText(LoginActivity.this, "С кем не бывает?", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tvSignup:
                        Toast.makeText(LoginActivity.this, "В разработке...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tvTerms:
                        Toast.makeText(LoginActivity.this, "1. Робот не может нанести вред человеку...", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };
        tvTerms.setOnClickListener(oclOther);
        tvForgot.setOnClickListener(oclOther);
        tvSignup.setOnClickListener(oclOther);
    }
}
