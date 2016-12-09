package itea.contactstest2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by shevc on 09.12.2016.
 */

public class ListEntity implements Parcelable {
    private List<ContactEntity> list;

    protected ListEntity(Parcel in) {
        list = in.createTypedArrayList(ContactEntity.CREATOR);
    }

    public static final Creator<ListEntity> CREATOR = new Creator<ListEntity>() {
        @Override
        public ListEntity createFromParcel(Parcel in) {
            return new ListEntity(in);
        }

        @Override
        public ListEntity[] newArray(int size) {
            return new ListEntity[size];
        }
    };

    public ListEntity(List<ContactEntity> list){
        this.list = list;
    }

    public List<ContactEntity> getList(){
        return list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(list);
    }
}
