package itea.contactstest2;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsActivity extends AppCompatActivity {
    private ListView lv;
    private ContactsAdapter ctAdapter;
    private Button btnTbCreate;
    private Toolbar toolbar;
    private TextView tvToolbarTitle;


    public static boolean flagImg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        lv = (ListView) findViewById(R.id.lvContacts);
        btnTbCreate = (Button) findViewById(R.id.btnTbCreate);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);

        setSupportActionBar(toolbar);


        if (!LaunchActivity.flagAUTH) {
            Bundle bundle = getIntent().getExtras();
            tvToolbarTitle.setText("Welcome, " + bundle.getString("USERNAME"));
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences(LaunchActivity.SP_NAME, MODE_PRIVATE);
            tvToolbarTitle.setText(sharedPreferences.getString(MainActivity.USERNAME, ""));
        }

        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + this.getResources().getResourcePackageName(R.drawable.contact_1)
                + '/' + this.getResources().getResourceTypeName(R.drawable.contact_1)
                + '/' + this.getResources().getResourceEntryName(R.drawable.contact_1) );

        ctAdapter = new ContactsAdapter(this, R.layout.each_item, flagImg , imageUri);
        lv.setAdapter(ctAdapter);
        lv.setLongClickable(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.colorPrimaryDark));
        }

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(ContactsActivity.this);
                final int posCopy = pos;
                adBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ctAdapter.deleteContactEntity(posCopy);
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(ContactsActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setTitle("Delete this contact?");
                adBuilder.create().show();
                return true;
            }
        });

        btnTbCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, CreateContactActivity.class);
                startActivityForResult(intent, 1122);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Settings");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(ContactsActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(ContactsActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1122){
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                ContactEntity contactEntity = b.getParcelable("CONTACTENTITY");
                ctAdapter.setContactEntity(contactEntity);
                ctAdapter.updateList(contactEntity);
            }
        }
    }
}
