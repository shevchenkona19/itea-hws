package itea.contactstest2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ImageView ivBackground;
    private CheckBox cbRemember;
    private SharedPreferences sharedPreferences;

    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        ivBackground = (ImageView) findViewById(R.id.ivBckg);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        try {
            File f = new File(this.getCacheDir(), "IMG");
            f.createNewFile();

            Bitmap bitmap = new BlurMaker().blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.bckg3));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
            byte[] bitmapData = bos.toByteArray();

            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapData);
            fos.flush();
            fos.close();

            Picasso.with(this)
                    .load(f)
                    .into(ivBackground);
        } catch (Exception e){
            e.printStackTrace();
        }

        getSupportActionBar().setTitle("Welcome!");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbRemember.isChecked()){
                    rememberUser(etUsername.getText().toString(), etPassword.getText().toString());
                }
                if (!etUsername.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {
                    Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                    intent.putExtra("USERNAME", etUsername.getText().toString());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Must to fill in the forms", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void rememberUser(String username, String password){
        LaunchActivity.flagAUTH = true;
        sharedPreferences = getSharedPreferences(LaunchActivity.SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);

        editor.putBoolean(LaunchActivity.SP_AUTHORISED, LaunchActivity.flagAUTH);
        editor.commit();
        return;
    }
}
