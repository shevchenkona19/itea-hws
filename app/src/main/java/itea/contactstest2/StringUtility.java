package itea.contactstest2;

import android.support.design.widget.TextInputLayout;

/**
 * Created by shevc on 08.12.2016.
 */

public class StringUtility {

    private final int MIN_PHONE_LENGTH = 10;
    private final int MAX_PHONE_LENGTH = 13;



    public boolean isNumeric(String s){
        if(s != null || !s.equals("")) {
            char[] number1 = s.toCharArray();
            boolean flag = false;
            for (char c : number1) {
                if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                        (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            return flag;
        }
        return false;
    }
    public boolean isNumeric(CharSequence s){
        if(s != null || !s.equals("")) {
            char[] number1 = s.toString().toCharArray();
            boolean flag = false;
            for (char c : number1) {
                if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                        (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            return flag;
        }
        return false;
    }

    public boolean isTel(CharSequence charSequence, TextInputLayout til) {
        try {
            switch (charSequence.charAt(0)) {
                case '+':
                    if (charSequence.length() < MAX_PHONE_LENGTH) {
                        til.setError("Less than 12 numbers");
                        return false;
                    }
                    char[] number = charSequence.subSequence(1, charSequence.length()).toString().toCharArray();
                    for (char c : number) {
                        if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                                (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                            til.setError("Phone number can contain only numbers!");
                            return false;
                        }
                    }
                    if (charSequence.length() > MAX_PHONE_LENGTH) {
                        til.setError("More than 12 numbers");
                        return false;
                    }
                    break;


                case '0':
                    if (charSequence.length() < MIN_PHONE_LENGTH) {
                        til.setError("Less than 10 numbers");
                        break;
                    } else if (charSequence.length() > MIN_PHONE_LENGTH) {
                        til.setError("More than 10 numbers");
                        return false;
                    } else {
                        char[] number1 = charSequence.subSequence(0, charSequence.length()).toString().toCharArray();
                        for (char c : number1) {
                            if ((c != '0') && (c != '1') && (c != '2') && (c != '3') && (c != '4') &&
                                    (c != '5') && (c != '6') && (c != '7') && (c != '8') && (c != '9')) {
                                til.setError("Phone number can contain only numbers!");
                                return false;
                            }
                        }
                    }
                    break;
                default:
                    til.setError("Enter a valid number");
                    return false;
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isNameCorrect(CharSequence charSequence){
        char[] name = charSequence.toString().toCharArray();
        boolean flag = true;
        for(char c : name){
            if(c =='=' || c =='+' || c =='_' || c =='/' || c =='|' || c =='?' || c =='!' || c =='@'
                    || c =='"' || c =='#' || c =='$' || c ==';' || c =='%'
                    || c =='^' || c ==':' || c =='*' || c =='(' || c ==')' || c =='.' || c =='`' || c =='~'){
                flag = false;

            } else {
                flag = true;
            }
            if (!flag){
                break;
            }
        }
        return flag;
    }

    public boolean isEmailCorrect(CharSequence charSequence){
        if(charSequence == "" || charSequence == null){
            return false;
        }
        char[] email = charSequence.toString().toCharArray();
        boolean flagDog = false;
        boolean flagBog = false;
        for(char c : email){
            if(c == '@'){
                flagDog = true;
            }
        }
        if(charSequence.toString().indexOf('@') < charSequence.toString().indexOf('.') &&
                charSequence.toString().indexOf('.') != -1){
            flagBog = true;
        }
        if (flagBog && flagDog){
            return true;
        } else {
            return false;
        }
    }

}
