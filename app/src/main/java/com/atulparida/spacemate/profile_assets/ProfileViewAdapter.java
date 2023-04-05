package com.atulparida.spacemate.profile_assets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.atulparida.spacemate.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProfileViewAdapter extends ArrayAdapter<UserListView> {

    private Context currContext;
    private int currResource;

    public ProfileViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserListView> objects) {
        super(context, resource, objects);
        this.currContext = context;
        this.currResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(currContext);
        convertView = layoutInflater.inflate(currResource, parent, false);

        ImageView img = convertView.findViewById(R.id.itemIcon);
        TextView headingText = convertView.findViewById(R.id.itemHeading);
        TextView subTitleText = convertView.findViewById(R.id.itemValue);

        img.setImageResource(getItem(position).getImg());
        headingText.setText(getItem(position).getHeading());
        subTitleText.setText(getItem(position).getSubtitle());

        return convertView;
    }
}
