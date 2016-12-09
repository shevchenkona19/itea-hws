package itea.contactstest2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    private ListView lv;
    private ContactsAdapter ctAdapter;
    private Button btnCreate;

    public static boolean flagImg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        lv = (ListView) findViewById(R.id.lvContacts);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        ctAdapter = new ContactsAdapter(this, R.layout.each_item, flagImg);
        lv.setAdapter(ctAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvTel= (TextView) findViewById(R.id.tvTel);
                Intent callIntent =new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+tvTel.getText().toString()));
                startActivity(callIntent);
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactsActivity.this, CreateContactActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Bundle b = data.getExtras();
                ContactEntity contactEntity = b.getParcelable("CONTACTENTITY");
                ctAdapter.setContactEntity(contactEntity);
                ctAdapter.updateList(new ImageView(getApplicationContext()));
            }
        }
    }
}
