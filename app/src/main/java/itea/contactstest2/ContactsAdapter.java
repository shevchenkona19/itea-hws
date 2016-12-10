package itea.contactstest2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shevc on 08.12.2016.
 */

public class ContactsAdapter extends ArrayAdapter<ImageView> {
        private List<ImageView> list;
        private Context context;
        private LayoutInflater inflater;
        private ContactEntity contactEntity;
        private int currPos;
        private boolean flagImg;

        public ContactsAdapter(Context context, int resource) {
            super(context, resource);
            list = new ArrayList<>();
            this.context = context;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currPos = -1;
        }
        public ContactsAdapter(Context context, int resource, boolean flag) {
            super(context, resource);
            list = new ArrayList<>();
            this.context = context;
            inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            currPos = -1;
            this.flagImg = flag;
        }

        public void setContactEntity(ContactEntity contactEntity){
        this.contactEntity = contactEntity;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        public List<ImageView> getList(){
            return list;
        }

        @Nullable
        @Override
        public ImageView getItem(int position) {
            return list.get(position);
        }

        public void updateList(ImageView ivNew){

            this.list.add(ivNew);
            notifyDataSetChanged();
            currPos +=1;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null){
                view = inflater.inflate(R.layout.each_item, parent, false);

            }
            ImageView ivFace = (ImageView) view.findViewById(R.id.ivFace);
            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvSurname = (TextView) view.findViewById(R.id.tvSurname);
            TextView tvTel = (TextView) view.findViewById(R.id.tvTel);
            TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            TextView tvCityCountry = (TextView) view.findViewById(R.id.tvCityCountry);

            if(position >= currPos) {
                tvName.setText(contactEntity.getName());
                tvSurname.setText(contactEntity.getSurname());
                tvTel.setText(contactEntity.getTel());
                tvEmail.setText(contactEntity.getEmail());
                tvCityCountry.setText(contactEntity.getCity() + ", " + contactEntity.getCountry());
                if (flagImg) {
                    Picasso.with(getContext())
                            .load(contactEntity.getUri())
                            .resize(64, 64)
                            .into(ivFace);
                }
            }

            return view;
        }
}

/*
* 1 дженерезируй адаптер классом КотактЕнтити
* 2 сделай так что б если пользовательне выбрал картинку то только тогда показывалась дефолтная
*
* */
