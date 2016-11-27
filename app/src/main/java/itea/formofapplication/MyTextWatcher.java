package itea.formofapplication;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by shevc on 27.11.2016.
 */

public class MyTextWatcher implements TextWatcher {
    public EditText[] et = new EditText[3];
    private String name, lastName, email;

    public MyTextWatcher(EditText[] et){
        super();
        this.et[0] = et[0];
        this.et[1] = et[1];
        this.et[2] = et[2];
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (et[0].getText().toString() != null) {
                name = et[0].getText().toString();
            }
            if (et[1].getText().toString() != null) {
                lastName = et[1].getText().toString();
            }
            if (et[2].getText().toString() != null) {
                email = et[2].getText().toString();
            }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
