package itea.contactstest2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {
    public static final String SP_NAME = "CONTACTS_LOGIN";
    public static final String SP_AUTHORISED = "AUTHORISED";
    public static boolean flagAUTH;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        flagAUTH = sharedPreferences.getBoolean(SP_AUTHORISED, flagAUTH);
        if(flagAUTH){
            Intent intent = new Intent(this, ContactsActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
