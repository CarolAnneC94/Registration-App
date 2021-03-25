package com.example.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class ListUsersAdapter extends ArrayAdapter<ArrayList<User>> {
    private final Context context;
    private final ArrayList<User> values;

    public ListUsersAdapter(Context context, int id , ArrayList<User> values) {
        super(context,R.layout.row_users);
        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_users, parent, false);
        TextView UserName = (TextView) rowView.findViewById(R.id.txUserName);
        TextView UserPassword = (TextView) rowView.findViewById(R.id.txUserPasw);
        UserName.setText("User Name: " + values.get(position).getUserName());
        UserPassword.setText("Password:" + values.get(position).getPassword());

        // Change icon based on name
        return rowView;
    }

    @Override
    public int getCount() {
        return values.size();
    }
}
