package itea.contactstest2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shevc on 08.12.2016.
 */

public class ContactEntity implements Parcelable {
    private String name;
    private String surname;
    private String tel;
    private String email;
    private String city;
    private String country;
    private String  uri;

    protected ContactEntity(Parcel in) {
        name = in.readString();
        surname = in.readString();
        tel = in.readString();
        email = in.readString();
        city = in.readString();
        country = in.readString();
        uri = in.readString();
    }

    public static final Creator<ContactEntity> CREATOR = new Creator<ContactEntity>() {
        @Override
        public ContactEntity createFromParcel(Parcel in) {
            return new ContactEntity(in);
        }

        @Override
        public ContactEntity[] newArray(int size) {
            return new ContactEntity[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getUri() {
        return uri;
    }

    public ContactEntity(String name, String surname, String tel,
                         String email, String city, String country, String uri) {
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.email = email;
        this.city = city;
        this.country = country;
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(tel);
        parcel.writeString(email);
        parcel.writeString(city);
        parcel.writeString(country);
        parcel.writeString(uri);
    }
}
