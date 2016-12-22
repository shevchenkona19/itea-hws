package itea.contactstest2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactInfoFragment extends DialogFragment {


    private ContactEntity contactEntity;


    public ContactInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static ContactInfoFragment newInstance(ContactEntity contactEntity) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("CONTACT", contactEntity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        contactEntity = args.getParcelable("CONTACT");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
        TextView tvFragmentName = (TextView) view.findViewById(R.id.tvFragmentName);
        TextView tvFragmentSurname = (TextView) view.findViewById(R.id.tvFragmentSurname);
        TextView tvFragmentEmail = (TextView) view.findViewById(R.id.tvFragmentEmail);
        TextView tvFragmentCityCountry = (TextView) view.findViewById(R.id.tvFragmentCityCountry);
        TextView tvFragmentPhone = (TextView) view.findViewById(R.id.tvFragmentPhone);
        ImageView ivFragmentFace = (ImageView) view.findViewById(R.id.ivFragmentFace);

        tvFragmentName.setText(contactEntity.getName());
        tvFragmentSurname.setText(contactEntity.getSurname());
        tvFragmentEmail.setText(contactEntity.getEmail());
        tvFragmentPhone.setText(contactEntity.getTel());
        tvFragmentCityCountry.setText(contactEntity.getCity() + ", " + contactEntity.getCountry());

        Picasso.with(getContext())
                .load(contactEntity.getUri())
                .into(ivFragmentFace);

        return view;
    }

}
