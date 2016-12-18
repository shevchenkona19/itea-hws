package itea.contactstest2;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class CreateContactActivity extends AppCompatActivity {

    private static final int REQUEST_GALLERY = 2211;
    private EditText etName;
    private EditText etSurname;
    private EditText etTel;
    private EditText etCountry;
    private EditText etCity;
    private EditText etEmail;
    private EditText etNotes;
    private Button btnSkip, btnSave, btnCancel;
    private ImageView ivSubnail;
    private TextInputLayout tilName, tilSurname, tilTel, tilCountry, tilCity, tilEmail;
    private boolean[] flags;
    private Context context;
    private Uri uri;
    private StringUtility strUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        flags = new boolean[6];

        ContactsActivity.flagImg = false;

        strUtil = new StringUtility();
        context = this;

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etTel = (EditText) findViewById(R.id.etTel);
        etCountry = (EditText) findViewById(R.id.etCountry);
        etCity = (EditText) findViewById(R.id.etCity);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNotes = (EditText) findViewById(R.id.etNotes);

        tilCity = (TextInputLayout) findViewById(R.id.tilCity);
        tilCountry = (TextInputLayout) findViewById(R.id.tilCountry);
        tilTel = (TextInputLayout) findViewById(R.id.tilTel);
        tilEmail = (TextInputLayout) findViewById(R.id.tilEmail);
        tilName = (TextInputLayout) findViewById(R.id.tilName);
        tilSurname = (TextInputLayout) findViewById(R.id.tilSurname);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSkip = (Button) findViewById(R.id.btnSkip);

        ivSubnail = (ImageView) findViewById(R.id.ivSubnail);

        ivSubnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flags[0] && flags[1] && flags[2] && flags[3] && flags[4] && flags[5]) {
                        Toast.makeText(CreateContactActivity.this, "Img selected", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(CreateContactActivity.this, ContactsActivity.class);
                        ContactEntity contactEntity = new ContactEntity(etName.getText().toString(), etSurname.getText().toString()
                                , etTel.getText().toString(), etEmail.getText().toString(),
                                etCity.getText().toString(), etCountry.getText().toString(), uri);
                        intent.putExtra("CONTACTENTITY", contactEntity);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                } else {
                    Toast.makeText(CreateContactActivity.this, "You have some errors", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etName.getText().toString().equals("") || !etSurname.getText().toString().equals("")
                        || !etTel.getText().toString().equals("") || !etEmail.getText().toString().equals("") ||
                        !etCity.getText().toString().equals("") || !etCountry.getText().toString().equals("")) {
                    Toast.makeText(CreateContactActivity.this, "You can only skip if every field is empty!", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Intent intent = new Intent(CreateContactActivity.this, ContactsActivity.class);
                    intent.putExtra("ENTITY", new ContactEntity("default", "default", "default",
                            "default", "default", "default", null));
                    setResult(RESULT_CANCELED, intent);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateContactActivity.this, ContactsActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!strUtil.isNumeric(charSequence.toString())) {
                    tilName.setError("You can't use numbers!");
                    flags[0] = false;
                    return;
                }
                flags[0] = true;
                tilName.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!strUtil.isNumeric(charSequence.toString())) {
                    tilSurname.setError("You can't use numbers!");
                    flags[1] = false;
                    return;
                }
                flags[1] = true;
                tilSurname.setError("");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etTel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    if (strUtil.isTel(charSequence, tilTel)) {
                        tilTel.setError("");
                        flags[2] = true;
                        return;
                    } else {
                        flags[2] = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!strUtil.isNameCorrect(charSequence)) {
                    tilCountry.setError("Country name can't contain symbols!");
                    flags[3] = false;
                } else {
                    tilCountry.setError("");
                    flags[3] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!strUtil.isNameCorrect(charSequence)) {
                    tilCity.setError("City name can't contain symbols!");
                    flags[4] = false;
                } else {
                    tilCity.setError("");
                    flags[4] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!strUtil.isEmailCorrect(charSequence)) {
                    tilEmail.setError("Email is incorrect");
                    flags[5] = false;
                } else {
                    tilEmail.setError("");
                    flags[5] = true;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select File"), REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_GALLERY) {
            uri = data.getData();
            ContactsActivity.flagImg = true;
            Picasso.with(context).load(uri).into(ivSubnail);
        }
    }
}

/*
* 5 сделай список 5 захардкоженых пользователей
* 6 избавься от скроллВью - я тебе  уже говорил это плохое решение
* 8 сделай так что б можно было передавать картинку в листАктивити
* */
